import request from './request'

export const addInventory = (data, token) => {
  return request({
    url: '/product',
    method: 'POST',
    data,
    params: {
      token: token
    }
  })
}
