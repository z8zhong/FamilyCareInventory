import request from './request'

export const updateShipTime = (data) => {
  return request({
    url: '/buy',
    method: 'PUT',
    data
  })
}
