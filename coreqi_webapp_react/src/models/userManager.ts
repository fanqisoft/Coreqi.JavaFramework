import { Subscription, Effect } from 'dva';
import { Reducer } from 'redux';
import { getUsers }from '@/services/userService'



export interface UserInfo {
  id?:number;
  userName?:string;
  passWord?:string;
  realName?:string;
  mobile?:string;
  address?:string;
  email?:string;
  createTime?:Date;
  loginTime?:Date;
  lastLoginTime?:Date;
  loginCount?:number;
  status?:number;
  isDel?:number;
}

export interface UserInfoModelState {
  userList?:UserInfo[];
}

export interface UserInfoModelType {
  namespace: 'userManager';
  state: UserInfoModelState;
  effects: {
    getUser: Effect;
  };
  reducers: {
    getUserSuccess: Reducer<UserInfoModelState>;
  };
  subscriptions: { setup : Subscription };
}

const UserInfoModel:UserInfoModelType = {
  namespace: 'userManager',

  state: {
    userList: [],
  },

  effects: {
    *getUser(_, { call, put }) {
      const response = yield call(getUsers);
      yield put({
        type: 'getUserSuccess',
        payload: response,
      });
    },
  },

  reducers: {
    getUserSuccess(state, action) {
      return {
        ...state,
        userList: action.payload.data.records || [],
      };
    },
  },
  subscriptions: {
    setup({ dispatch,history }){
      // Subscribe history(url) change, trigger `load` action if pathname is `/`
      return history.listen(location => {
        const {pathname,search,state} = location;
        switch (pathname) {
          case '/userManager':
            dispatch({
              type: 'getUser',
            })
            break
          default:
            break
        }
      });
    },
  },
}

export default UserInfoModel;

// export interface TableListItem {
//   key: number;
//   disabled?: boolean;
//   href: string;
//   avatar: string;
//   name: string;
//   owner: string;
//   desc: string;
//   callNo: number;
//   status: number;
//   updatedAt: Date;
//   createdAt: Date;
//   progress: number;
// }
//
// export interface TableListPagination {
//   total: number;
//   pageSize: number;
//   current: number;
// }
//
// export interface TableListData {
//   list: TableListItem[];
//   pagination: Partial<TableListPagination>;
// }
//
// export interface TableListParams {
//   sorter?: string;
//   status?: string;
//   name?: string;
//   desc?: string;
//   key?: number;
//   pageSize?: number;
//   currentPage?: number;
// }
