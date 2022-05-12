import request from './request'

export const myTransSum = (token) => {
  return request({
    url: '/transsum',
    method: 'GET',
    params: {
      token: token
    }
  })
}
