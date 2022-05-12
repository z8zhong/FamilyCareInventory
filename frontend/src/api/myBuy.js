import request from './request'

export const myBuys = (token) => {
  return request({
    url: '/buy',
    method: 'GET',
    params: {
      token: token
    }
  })
}
