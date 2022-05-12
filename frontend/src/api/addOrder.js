import request from './request'

export const addOrder = (data, token) => {
  return request({
    url: '/order',
    method: 'POST',
    params: {
      token: token
    },
    data
  })
}
