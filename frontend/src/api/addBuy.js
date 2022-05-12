import request from './request'

export const addBuy = (data, token) => {
  return request({
    url: '/buy',
    method: 'POST',
    params: {
      token: token
    },
    data
  })
}
