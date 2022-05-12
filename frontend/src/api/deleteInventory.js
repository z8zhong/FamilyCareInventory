import request from './request'

export const deleteInventory = (data) => {
  return request({
    url: '/product',
    method: 'DELETE',
    params: {
      token: data.token,
      productId: data.productId
    }
  })
}
