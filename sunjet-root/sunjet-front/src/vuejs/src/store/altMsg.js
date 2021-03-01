
import { Message } from 'element-ui'


export const error = (message)=>{
  Message({
    message,
    type: 'error',
    duration: 5 * 1000,
  })
}


