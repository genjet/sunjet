import Vue from 'vue'
import ElementUI from 'element-ui'
// import i18n from '@inits/vueInit/vueI18nInit'
import lang from "../lang";
// import { AppModule } from '@/store/modules/app'

export default () => {
    Vue.use(ElementUI, {
        // size: AppModule.size,
        i18n: (key: string, value: string) => lang.i18n.t(key, value),
    })
    Vue.prototype.$I18N = lang.I18N;
}
