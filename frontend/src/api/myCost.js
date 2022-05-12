import request from './request'

export const myCosts = (token) => {
  return request({
    url: '/cost',
    method: 'GET',
    params: {
      token: token
    }
  })
}
