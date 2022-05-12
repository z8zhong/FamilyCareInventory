import request from './request'

export const addCost = (data, token) => {
  return request({
    url: '/cost',
    method: 'POST',
    params: {
      token: token
    },
    data
  })
}
