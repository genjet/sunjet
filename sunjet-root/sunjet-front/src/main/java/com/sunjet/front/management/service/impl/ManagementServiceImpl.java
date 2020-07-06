package com.sunjet.front.management.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjAuthorityRepository;
import com.sunjet.common.dao.SjDepRepository;
import com.sunjet.common.dao.SjRoleAuthorityRelRepository;
import com.sunjet.common.dao.SjRoleRepository;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjAuthority;
import com.sunjet.common.entity.SjDep;
import com.sunjet.common.entity.SjRole;
import com.sunjet.common.entity.SjRoleAuthorityRel;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.SjUserRoleRel;
import com.sunjet.front.common.vo.OptionVo;
import com.sunjet.front.management.service.ManagementService;
import com.sunjet.front.management.vo.AuthorityVo;
import com.sunjet.front.management.vo.DepVo;
import com.sunjet.front.management.vo.RoleVo;
import com.sunjet.front.management.vo.UserVo;
import com.sunjet.front.util.ToolUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {
	@Autowired
	private SjUserRepository sjUserRepository;
	@Autowired
	private SjRoleRepository sjRoleRepository;
	@Autowired
	private SjDepRepository sjDepRepository;
	@Autowired
	private SjRoleAuthorityRelRepository sjRoleAuthorityRelRepository;
	@Autowired
	private SjAuthorityRepository sjAuthorityRepository;

	public static String ROLE = "ROLE_";

	@Override
	public List<UserVo> getAllUserVo() {
		List<SjUser> sjUsers = sjUserRepository.findAll();
		List<UserVo> rtnVOs = new ArrayList<UserVo>();
		// String id = 1;
		for (SjUser sjUser : sjUsers) {
			UserVo user = new UserVo();
			user.setOid(sjUser.getOid());
			user.setEmpId(sjUser.getEmpId());
			user.setAccount(sjUser.getAccount());
			user.setName(sjUser.getName());
			user.setAvatar("Sorceress-Witch");
			user.setPwd(null == sjUser.getPwd() ? "" : sjUser.getPwd());
			user.setDep(null != sjUser.getSjDep() ? sjUser.getSjDep().getName() : "");
			user.setNickName(null != sjUser.getNickName() ? sjUser.getNickName() : "");
			user.setEnabled(null == sjUser.getEnabled() ? Boolean.TRUE : sjUser.getEnabled());
			user.setArrivalDay(null == sjUser.getArrivalDay() ? LocalDate.now() : sjUser.getArrivalDay());
			final List<String> roles = sjUser.getSjUserRoleRels().stream().map(it -> it.getSjRole().getRoleName())
					.collect(Collectors.toList());
			user.setRoles(roles);
			rtnVOs.add(user);
		}
		return rtnVOs;

	}

	@Override
	public List<DepVo> getAllDepVO() {
		List<DepVo> rtnVOs = new ArrayList<DepVo>();
		List<SjDep> deps = sjDepRepository.findAll();
		if (CollectionUtils.isNotEmpty(deps)) {
			for (SjDep sjDep : deps) {
				DepVo depVO = new DepVo();
				depVO.setKey(sjDep.getOid());
				depVO.setValue(sjDep.getName());
				rtnVOs.add(depVO);
			}
		}
		return rtnVOs;
	}

	@Override
	public UserVo addUser(UserVo userVO) {
		UserVo rtnUserVO = userVO;
		SjUser topSjUser = sjUserRepository.findTopByOrderByEmpIdDesc();
		String empId = ToolUtil.addOne4Str(topSjUser.getEmpId());
		userVO.setEmpId(empId);
		log.info("new empId is {}", empId);

		SjUser user = new SjUser();
		user.setEmpId(userVO.getEmpId());
		user.setAccount(userVO.getAccount());
		user.setName(userVO.getName());
		user.setPwd(userVO.getPwd());
		user.setEnabled(Boolean.TRUE);
		user.setNickName(userVO.getNickName());
		Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
		if (sjDep.isPresent()) {
			user.setSjDep(sjDep.get());
			rtnUserVO.setDep(sjDep.get().getName());
		}
		user.setArrivalDay(userVO.getArrivalDay());
		List<SjUserRoleRel> sjUserRoleRels = new ArrayList<>();
		for (String roleCode : userVO.getRoles()) {
			SjUserRoleRel addSjUserRoleRel = new SjUserRoleRel();
			addSjUserRoleRel.setSjUser(user);
			addSjUserRoleRel.setSjRole(sjRoleRepository.findByRoleCode(roleCode));
			sjUserRoleRels.add(addSjUserRoleRel);
		}
		user.getSjUserRoleRels().addAll(sjUserRoleRels);
		sjUserRepository.save(user);
		return rtnUserVO;
	}

	@Override
	public UserVo updateUser(UserVo userVO) {
		UserVo rtnUserVO = userVO;
		Optional<SjUser> sjUserOpt = sjUserRepository.findByEmpId(userVO.getEmpId());
		if (sjUserOpt.isPresent()) {
			SjUser user = sjUserOpt.get();
			user.setAccount(userVO.getAccount());
			user.setName(userVO.getName());
			user.setPwd(userVO.getPwd());
			user.setEnabled(userVO.getEnabled());
			user.setNickName(userVO.getNickName());
			Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
			if (sjDep.isPresent()) {
				user.setSjDep(sjDep.get());
				rtnUserVO.setDep(sjDep.get().getName());
			}
			user.setArrivalDay(userVO.getArrivalDay());
			List<SjUserRoleRel> sjUserRoleRels = new ArrayList<>();
			for (String roleCode : userVO.getRoles()) {
				SjUserRoleRel addSjUserRoleRel = new SjUserRoleRel();
				addSjUserRoleRel.setSjUser(user);
				addSjUserRoleRel.setSjRole(sjRoleRepository.findByRoleCode(roleCode));
				sjUserRoleRels.add(addSjUserRoleRel);
			}
			user.getSjUserRoleRels().clear();
			user.getSjUserRoleRels().addAll(sjUserRoleRels);
			rtnUserVO.setRoles(
					sjUserRoleRels.stream().map(it -> it.getSjRole().getRoleName()).collect(Collectors.toList()));
			sjUserRepository.save(user);
		}
		return rtnUserVO;
	}

	@Override
	public UserVo deleteUser(String oid) {
		UserVo rtnUserVO = new UserVo();
		Optional<SjUser> userOpt = sjUserRepository.findById(oid);
		if (userOpt.isPresent()) {
			SjUser sjUser = userOpt.get();
			rtnUserVO.setName(sjUser.getName());
			sjUserRepository.delete(sjUser);
		}
		return rtnUserVO;
	}

	@Override
	public List<RoleVo> getAllRoleVo() {
		List<RoleVo> rtnVOs = new ArrayList<RoleVo>();
		List<SjRole> sjRoles = sjRoleRepository.findAll();
		for (SjRole sjRole : sjRoles) {
			RoleVo roleVO = new RoleVo();
			roleVO.setOid(sjRole.getOid());
			roleVO.setRoleCode(sjRole.getRoleCode().substring(5));
			roleVO.setRoleName(sjRole.getRoleName());
			List<SjRoleAuthorityRel> SjRoleAuthorityRels = sjRole.getSjRoleAuthorityRels();

			List<AuthorityVo> authorityVos = SjRoleAuthorityRels.stream().map(it -> {
				SjAuthority sjAuthority = it.getSjAuthority();
				AuthorityVo authorityVo = new AuthorityVo();
				BeanUtils.copyProperties(sjAuthority, authorityVo);
				return authorityVo;
			}).collect(Collectors.toList());
			roleVO.setAuthorityVo(authorityVos);
			rtnVOs.add(roleVO);
		}
		return rtnVOs;
	}

	@Override
	public List<OptionVo> getRoleOptionVos() {
		final List<OptionVo> rtnVos = new ArrayList<OptionVo>();
		final List<SjRole> sjRoles = sjRoleRepository.findAll();
		for (SjRole sjRole : sjRoles) {
			OptionVo vo = new OptionVo(sjRole.getRoleName(), sjRole.getRoleCode());
			rtnVos.add(vo);
		}
		return rtnVos;
	}

	@Override
	public RoleVo addRole(RoleVo roleVo) {
		SjRole sjRole = new SjRole();
		String realCode = ROLE.concat(roleVo.getRoleCode());
		sjRole.setRoleCode(realCode.toUpperCase());
		sjRole.setRoleName(roleVo.getRoleName());
		List<SjRoleAuthorityRel> sjRoleAuthorityRels = new ArrayList<>();
		List<SjAuthority> sjAuthorities = sjAuthorityRepository.findByAuthorityCodeIn(roleVo.getAuthority());
		for (SjAuthority sjAuthority : sjAuthorities) {
			SjRoleAuthorityRel sjRoleAuthorityRel = new SjRoleAuthorityRel();
			sjRoleAuthorityRel.setSjRole(sjRole);
			sjRoleAuthorityRel.setSjAuthority(sjAuthority);
			sjRoleAuthorityRels.add(sjRoleAuthorityRel);
		}
		sjRole.setSjRoleAuthorityRels(sjRoleAuthorityRels);
		sjRoleRepository.save(sjRole);
		this.bindAuthorityVoByDB(roleVo, sjRole);
		roleVo.setRoleCode(roleVo.getRoleCode().toUpperCase());
		return roleVo;
	}

	@Override
	public RoleVo updateRole(RoleVo roleVo) {
		Optional<SjRole> sjRoleOpt = sjRoleRepository.findById(roleVo.getOid());
		if (sjRoleOpt.isPresent()) {
			final SjRole sjRole = sjRoleOpt.get();
			sjRole.setRoleCode(ROLE.concat(roleVo.getRoleCode()).toUpperCase());
			sjRole.setRoleName(roleVo.getRoleName());
			List<SjRoleAuthorityRel> sjRoleAuthorityRels = sjRole.getSjRoleAuthorityRels();

			List<SjAuthority> sjAuthorities = sjAuthorityRepository.findByAuthorityCodeIn(roleVo.getAuthority());
			if (CollectionUtils.isNotEmpty(roleVo.getAuthority())) {
				if (CollectionUtils.isEmpty(sjRoleAuthorityRels)) {
					for (SjAuthority sjAuthority : sjAuthorities) {
						SjRoleAuthorityRel sjRoleAuthorityRel = new SjRoleAuthorityRel();
						sjRoleAuthorityRel.setSjRole(sjRole);
						sjRoleAuthorityRel.setSjAuthority(sjAuthority);
						sjRoleAuthorityRels.add(sjRoleAuthorityRel);
					}
				} else {
					List<String> removeCode = new ArrayList<String>();
					for (int i = 0; i < sjRoleAuthorityRels.size(); i++) {
						SjRoleAuthorityRel sjRoleAuthorityRel = sjRoleAuthorityRels.get(i);
						String code = sjRoleAuthorityRel.getSjAuthority().getAuthorityCode();
						if (!roleVo.getAuthority().contains(code)) {
							removeCode.add(code);
							sjRoleAuthorityRels.remove(i);
						}
					}
					for (SjAuthority sjAuthority : sjAuthorities) {
						boolean isAdd = Boolean.TRUE;
						for (SjRoleAuthorityRel sjRoleAuthorityRel : sjRoleAuthorityRels) {
							String code = sjRoleAuthorityRel.getSjAuthority().getAuthorityCode();
							if (sjAuthority.getAuthorityCode().equals(code)) {
								isAdd = Boolean.FALSE;
								break;
							}
						}
						if (isAdd) {
							SjRoleAuthorityRel sjRoleAuthorityRel = new SjRoleAuthorityRel();
							sjRoleAuthorityRel.setSjRole(sjRole);
							sjRoleAuthorityRel.setSjAuthority(sjAuthority);
							sjRoleAuthorityRels.add(sjRoleAuthorityRel);
						}
					}
					sjRole.setSjRoleAuthorityRels(sjRoleAuthorityRels);
				}
			} else {
				sjRoleAuthorityRels.clear();
			}
			sjRoleRepository.save(sjRole);
			if (CollectionUtils.isEmpty(sjRole.getSjRoleAuthorityRels())) {
				roleVo.getAuthorityVo().clear();
			} else {
				this.bindAuthorityVoByDB(roleVo, sjRole);
			}

		}
		return roleVo;
	}

	private void bindAuthorityVoByDB(RoleVo roleVo, final SjRole sjRole) {
		roleVo.setAuthorityVo(sjRole.getSjRoleAuthorityRels().stream().map(it -> {
			SjAuthority sjAuthority = it.getSjAuthority();
			AuthorityVo vo = new AuthorityVo();
			vo.setOid(sjAuthority.getOid());
			vo.setAuthorityCode(sjAuthority.getAuthorityCode());
			vo.setAuthorityName(sjAuthority.getAuthorityName());
			return vo;
		}).collect(Collectors.toList()));
	}

	@Override
	public List<OptionVo> getDepOptionVos() {
		final List<OptionVo> rtnVos = new ArrayList<OptionVo>();
		final List<SjDep> deps = sjDepRepository.findAll();
		for (SjDep dep : deps) {
			OptionVo vo = new OptionVo(dep.getName(), dep.getOid());
			rtnVos.add(vo);
		}
		return rtnVos;
	}

}
