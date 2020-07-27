import request from '@/utils/request';

export async function query() {
  return request('/api/users');
}
export async function queryCurrent() {
  return request('/api/currentUser');
}
export async function queryMenusByUserId(id) {
  return request(`/api/getMenusByUserId/${id}`)
}
export async function queryNotices() {
  return request('/api/notices');
}
