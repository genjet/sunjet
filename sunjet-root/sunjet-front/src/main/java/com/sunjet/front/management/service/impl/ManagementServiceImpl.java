package com.sunjet.front.management.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.sunjet.front.common.exception.ValidateErrorException;
import com.sunjet.front.common.services.CommonService;
import com.sunjet.front.common.vo.FailureVo;
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

	@Autowired
	CommonService CommonService;

	public static String ROLE = "ROLE_";

	@Override
	public List<UserVo> getAllUserVo() {
		final List<SjUser> sjUsers = sjUserRepository.findAll();
		final List<UserVo> rtnVOs = new ArrayList<UserVo>();
		// String id = 1;
		for (final SjUser sjUser : sjUsers) {
			final UserVo user = new UserVo();
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
		final List<DepVo> rtnVOs = new ArrayList<DepVo>();
		final List<SjDep> deps = sjDepRepository.findAll();
		if (CollectionUtils.isNotEmpty(deps)) {
			for (final SjDep sjDep : deps) {
				final DepVo depVO = new DepVo();
				depVO.setKey(sjDep.getOid());
				depVO.setValue(sjDep.getName());
				rtnVOs.add(depVO);
			}
		}
		return rtnVOs;
	}

	@Override
	public UserVo addUser(UserVo userVO) {
		final UserVo rtnUserVO = userVO;
		final SjUser topSjUser = sjUserRepository.findTopByOrderByEmpIdDesc();
		final String empId = ToolUtil.addOne4Str(topSjUser.getEmpId());
		userVO.setEmpId(empId);
		log.info("new empId is {}", empId);

		final SjUser user = new SjUser();
		user.setEmpId(userVO.getEmpId());
		user.setAccount(userVO.getAccount());
		user.setName(userVO.getName());
		user.setPwd(userVO.getPwd());
		user.setEnabled(Boolean.TRUE);
		user.setNickName(userVO.getNickName());
		final Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
		if (sjDep.isPresent()) {
			user.setSjDep(sjDep.get());
			rtnUserVO.setDep(sjDep.get().getName());
		}
		user.setArrivalDay(userVO.getArrivalDay());
		final List<SjUserRoleRel> sjUserRoleRels = new ArrayList<>();
		for (final String roleCode : userVO.getRoles()) {
			final SjUserRoleRel addSjUserRoleRel = new SjUserRoleRel();
			addSjUserRoleRel.setSjUser(user);
			final Optional<SjRole> sjRoleOpt = sjRoleRepository.findByRoleCode(roleCode);
			if(sjRoleOpt.isPresent()){
				addSjUserRoleRel.setSjRole(sjRoleOpt.get());
			}
			sjUserRoleRels.add(addSjUserRoleRel);
		}
		user.getSjUserRoleRels().addAll(sjUserRoleRels);
		sjUserRepository.save(user);
		return rtnUserVO;
	}

	@Override
	public UserVo updateUser(UserVo userVO) {
		final UserVo rtnUserVO = userVO;
		final Optional<SjUser> sjUserOpt = sjUserRepository.findByEmpId(userVO.getEmpId());
		if (sjUserOpt.isPresent()) {
			final SjUser user = sjUserOpt.get();
			user.setAccount(userVO.getAccount());
			user.setName(userVO.getName());
			user.setPwd(userVO.getPwd());
			user.setEnabled(userVO.getEnabled());
			user.setNickName(userVO.getNickName());
			final Optional<SjDep> sjDep = sjDepRepository.findById(userVO.getDep());
			if (sjDep.isPresent()) {
				user.setSjDep(sjDep.get());
				rtnUserVO.setDep(sjDep.get().getName());
			}
			user.setArrivalDay(userVO.getArrivalDay());
			final List<SjUserRoleRel> sjUserRoleRels = new ArrayList<>();
			for (final String roleCode : userVO.getRoles()) {
				final SjUserRoleRel addSjUserRoleRel = new SjUserRoleRel();
				addSjUserRoleRel.setSjUser(user);
				final Optional<SjRole> sjRoleOpt = sjRoleRepository.findByRoleCode(roleCode);
				if(sjRoleOpt.isPresent()){
					addSjUserRoleRel.setSjRole(sjRoleOpt.get());
				}
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
		final UserVo rtnUserVO = new UserVo();
		final Optional<SjUser> userOpt = sjUserRepository.findById(oid);
		if (userOpt.isPresent()) {
			final SjUser sjUser = userOpt.get();
			rtnUserVO.setName(sjUser.getName());
			sjUserRepository.delete(sjUser);
		}
		return rtnUserVO;
	}

	@Override
	public List<RoleVo> getAllRoleVo() {
		final List<RoleVo> rtnVOs = new ArrayList<RoleVo>();
		final List<SjRole> sjRoles = sjRoleRepository.findAll();
		for (final SjRole sjRole : sjRoles) {
			final RoleVo roleVO = new RoleVo();
			roleVO.setOid(sjRole.getOid());
			roleVO.setRoleCode(sjRole.getRoleCode().substring(5));
			roleVO.setRoleName(sjRole.getRoleName());
			final List<SjRoleAuthorityRel> SjRoleAuthorityRels = sjRole.getSjRoleAuthorityRels();

			final List<AuthorityVo> authorityVos = SjRoleAuthorityRels.stream().map(it -> {
				final SjAuthority sjAuthority = it.getSjAuthority();
				final AuthorityVo authorityVo = new AuthorityVo();
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
		for (final SjRole sjRole : sjRoles) {
			final OptionVo vo = new OptionVo(sjRole.getRoleName(), sjRole.getRoleCode());
			rtnVos.add(vo);
		}
		return rtnVos;
	}

	@Override
	public RoleVo addRole(RoleVo roleVo) {
		final SjRole sjRole = new SjRole();
		final String realCode = ROLE.concat(roleVo.getRoleCode());
		sjRole.setRoleCode(realCode.toUpperCase());
		sjRole.setRoleName(roleVo.getRoleName());
		final List<SjRoleAuthorityRel> sjRoleAuthorityRels = new ArrayList<>();
//		List<SjAuthority> sjAuthorities = sjAuthorityRepository.findByAuthorityCodeIn(roleVo.getAuthority());
//		for (SjAuthority sjAuthority : sjAuthorities) {
//			SjRoleAuthorityRel sjRoleAuthorityRel = new SjRoleAuthorityRel();
//			sjRoleAuthorityRel.setSjRole(sjRole);
//			sjRoleAuthorityRel.setSjAuthority(sjAuthority);
//			sjRoleAuthorityRels.add(sjRoleAuthorityRel);
//		}
//		sjRole.setSjRoleAuthorityRels(sjRoleAuthorityRels);
		sjRoleRepository.save(sjRole);
		this.bindAuthorityVoByDB(roleVo, sjRole);
		roleVo.setRoleCode(roleVo.getRoleCode().toUpperCase());
		return roleVo;
	}

	@Override
	public RoleVo updateRole(RoleVo roleVo) {
		final Optional<SjRole> sjRoleOpt = sjRoleRepository.findById(roleVo.getOid());
		if (sjRoleOpt.isPresent()) {
			final Map<SjAuthority, List<SjAuthority>> parentOptions = CommonService.getAllAuthoritys();
			final SjRole sjRole = sjRoleOpt.get();
			sjRole.setRoleCode(ROLE.concat(roleVo.getRoleCode()).toUpperCase());
			sjRole.setRoleName(roleVo.getRoleName());
			final List<SjRoleAuthorityRel> sjRoleAuthorityRels = sjRole.getSjRoleAuthorityRels();
			final List<List<String>> authorityList = roleVo.getAuthority();
			final Map<String, List<String>> map = new HashMap<String, List<String>>();
			if(CollectionUtils.isNotEmpty(authorityList)){
				authorityList.stream().forEach(it -> {
					final Object[] array =  it.toArray();

//					it.stream().forEach(a -> {System.out.println(a);});
//					System.out.println((String)it.toArray()[0]);
					String s = null;
					if(array.length > 1){
						s =(String)array[1];
					}
					if (map.containsKey(array[0])) {
						map.get(array[0]).add(s);
					}else{
						final List<String> ll = new ArrayList<>();
						ll.add(s);
						map.put((String)array[0], ll);
					}
//					 it.stream().collect(Collectors.groupingBy(a -> a[0], Collectors.mapping(a -> a[1], Collectors.toList())));
				});
//				final Map<String, List<String>> bindAuthorityOptionVoMap = authorityList.stream().map(list -> list.stream().collect(
//						Collectors.groupingBy(a -> a[0], Collectors.mapping(a -> a[1], Collectors.toList()))));
				for (final String key : map.keySet()) {
					for (final String string : map.get(key)) {
//						if(list.size() == 1){
							System.out.println("A: "+string);
//						}else{
//							System.out.println("B: "+string);
//						}
					}
				}
			}

//			List<SjAuthority> sjAuthorities = sjAuthorityRepository.findByAuthorityCodeIn(roleVo.getAuthority());
//			if (CollectionUtils.isNotEmpty(roleVo.getAuthority())) {
//				if (CollectionUtils.isEmpty(sjRoleAuthorityRels)) {
//					for (SjAuthority sjAuthority : sjAuthorities) {
//						SjRoleAuthorityRel sjRoleAuthorityRel = new SjRoleAuthorityRel();
//						sjRoleAuthorityRel.setSjRole(sjRole);
//						sjRoleAuthorityRel.setSjAuthority(sjAuthority);
//						sjRoleAuthorityRels.add(sjRoleAuthorityRel);
//					}
//				} else {
//					List<String> removeCode = new ArrayList<String>();
//					for (int i = 0; i < sjRoleAuthorityRels.size(); i++) {
//						SjRoleAuthorityRel sjRoleAuthorityRel = sjRoleAuthorityRels.get(i);
//						String code = sjRoleAuthorityRel.getSjAuthority().getAuthorityCode();
//						if (!roleVo.getAuthority().contains(code)) {
//							removeCode.add(code);
//							sjRoleAuthorityRels.remove(i);
//						}
//					}
//					for (SjAuthority sjAuthority : sjAuthorities) {
//						boolean isAdd = Boolean.TRUE;
//						for (SjRoleAuthorityRel sjRoleAuthorityRel : sjRoleAuthorityRels) {
//							String code = sjRoleAuthorityRel.getSjAuthority().getAuthorityCode();
//							if (sjAuthority.getAuthorityCode().equals(code)) {
//								isAdd = Boolean.FALSE;
//								break;
//							}
//						}
//						if (isAdd) {
//							SjRoleAuthorityRel sjRoleAuthorityRel = new SjRoleAuthorityRel();
//							sjRoleAuthorityRel.setSjRole(sjRole);
//							sjRoleAuthorityRel.setSjAuthority(sjAuthority);
//							sjRoleAuthorityRels.add(sjRoleAuthorityRel);
//						}
//					}
//					sjRole.setSjRoleAuthorityRels(sjRoleAuthorityRels);
//				}
//			} else {
//				sjRoleAuthorityRels.clear();
//			}
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
			final SjAuthority sjAuthority = it.getSjAuthority();
			final AuthorityVo vo = new AuthorityVo();
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
		for (final SjDep dep : deps) {
			final OptionVo vo = new OptionVo(dep.getName(), dep.getOid());
			rtnVos.add(vo);
		}
		return rtnVos;
	}

	@Override
	public RoleVo deleteRole(String roleCode) {
		final RoleVo rtnVO = new RoleVo();
		final Optional<SjRole> roleOpt = sjRoleRepository.findById(roleCode);
		if (roleOpt.isPresent()) {
			final SjRole sjRole = roleOpt.get();
			if(CollectionUtils.isNotEmpty(sjRole.getSjUserRoleRels())){
				throw new ValidateErrorException(new FailureVo("尚有使用者,不可刪除"));
			}else{
				rtnVO.setRoleName(sjRole.getRoleName());
				sjRoleRepository.delete(sjRole);
			}
		}else{
			throw new ValidateErrorException(new FailureVo(" Data Not Found ! "));
		}
		return rtnVO;
	}
}
