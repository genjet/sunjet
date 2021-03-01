import Vue from 'vue'
import VueI18n from 'vue-i18n'
import Cookies from 'js-cookie'
import elementEnLocale from 'element-ui/lib/locale/lang/en' // element-ui lang
import elementTWLocale from 'element-ui/lib/locale/lang/zh-TW' // element-ui lang
import enLocale from './en-US'
import twLocale from './zh-TW'

Vue.use(VueI18n)

const messages = {
  en: {
    ...enLocale,
    ...elementEnLocale
  },
  tw: {
    ...twLocale,
    ...elementTWLocale
  }
}
export function getLanguage() {
  const chooseLanguage = Cookies.get('language')
  if (chooseLanguage) return chooseLanguage

  // if has not choose language
  const language = (navigator.language || navigator.browserLanguage).toLowerCase()
  const locales = Object.keys(messages)
  for (const locale of locales) {
    if (language.indexOf(locale) > -1) {
      return locale
    }
  }
  return 'tw'
}
const i18n = new VueI18n({
  // set locale
  // options: en | zh | es
  locale: getLanguage(),
  // set locale messages
  messages
})

export const I18N =  (...text)=>{
  let message = "";
  text.forEach((item) => {
    message += i18n.t(item);
  });
  return message;
}


export default {
  i18n,
  I18N
}
