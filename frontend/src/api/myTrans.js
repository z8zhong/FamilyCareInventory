import request from './request'

export const myTrans = (token) => {
  return request({
    url: '/trans',
    method: 'GET',
    params: {
      token: token
    }
  })
}
