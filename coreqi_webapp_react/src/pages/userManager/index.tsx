import { DownOutlined, PlusOutlined } from '@ant-design/icons';
import { Button, Dropdown, Menu, message } from 'antd';
import React, { useState, useRef } from 'react';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import ProTable, { ProColumns, ActionType } from '@ant-design/pro-table';
import { SorterResult } from 'antd/es/table/interface';
import CreateForm from './components/CreateForm';
import UpdateForm, { FormValueType } from './components/UpdateForm';
import {UserInfo, UserInfoModelState} from '@/models/userManager'
import { Dispatch, AnyAction } from 'redux';
import { connect } from 'dva';
import { ConnectState } from '@/models/connect';

interface UserManagerProps {
  dispatch:Dispatch<AnyAction>;
  userList?:UserInfoModelState;
  submitting?:boolean;
}



/**
 * 添加用户
 * @param fields
 */
const handleAdd = async (fields: UserInfo) => {
  const hide = message.loading('正在添加');
  try {
    await addRule({ ...fields });
    hide();
    message.success('添加成功');
    return true;
  } catch (error) {
    hide();
    message.error('添加失败请重试！');
    return false;
  }
};

/**
 * 更新节点
 * @param fields
 */
const handleUpdate = async (fields: FormValueType) => {
  const hide = message.loading('正在配置');
  try {
    await updateRule({
      name: fields.name,
      desc: fields.desc,
      key: fields.key,
    });
    hide();

    message.success('配置成功');
    return true;
  } catch (error) {
    hide();
    message.error('配置失败请重试！');
    return false;
  }
};

/**
 *  删除节点
 * @param selectedRows
 */
const handleRemove = async (selectedRows: UserInfo[]) => {
  const hide = message.loading('正在删除');
  if (!selectedRows) return true;
  try {
    await removeRule({
      key: selectedRows.map(row => row.id),
    });
    hide();
    message.success('删除成功，即将刷新');
    return true;
  } catch (error) {
    hide();
    message.error('删除失败，请重试');
    return false;
  }
};

const TableList: React.FC<UserManagerProps> = props => {
  console.log(props);
  const { userList } = props;
  console.log(userList?.userList);
  const [sorter, setSorter] = useState<string>('');
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [stepFormValues, setStepFormValues] = useState({});
  const actionRef = useRef<ActionType>();
  const columns: ProColumns<UserInfo>[] = [
    // {
    //   title: '规则名称',
    //   dataIndex: 'name',
    //   rules: [
    //     {
    //       required: true,
    //       message: '规则名称为必填项',
    //     },
    //   ],
    // },
    {
      title: '用户Id',
      dataIndex: 'id',
      valueType: 'index',
    },
    {
      title: '用户名',
      dataIndex: 'userName',
      valueType:'text',
    },
    {
      title:'用户密码',
      dataIndex: 'passWord',
      valueType: 'text',
    },
    {
      title:'真实姓名',
      dataIndex: 'realName',
      valueType:'text',
    },
    {
      title:'手机号码',
      dataIndex: 'mobile',
      valueType: 'text',
    },
    {
      title:'住址',
      dataIndex: 'address',
      valueType: 'text',
    },
    {
      title:'电子邮件',
      dataIndex: 'email',
      valueType: 'text',
    },
    {
      title:'创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
    },
    {
      title:'登录时间',
      dataIndex: 'loginTime',
      valueType: 'dateTime',
    },
    {
      title:'最后一次登录时间',
      dataIndex: 'lastLoginTime',
      valueType: 'dateTime',
    },
    {
      title:'登录次数',
      dataIndex: 'loginCount',
      valueType: 'text',
    },
    {
      title:'用户状态',
      dataIndex: 'status',
      valueType: 'text',
    },
    {
      title:'是否删除',
      dataIndex: 'isDel',
      valueType: 'text',
    },
    // {
    //   title: '服务调用次数',
    //   dataIndex: 'callNo',
    //   sorter: true,
    //   hideInForm: true,
    //   renderText: (val: string) => `${val} 万`,
    // },
    // {
    //   title: '状态',
    //   dataIndex: 'status',
    //   hideInForm: true,
    //   valueEnum: {
    //     0: { text: '关闭', status: 'Default' },
    //     1: { text: '运行中', status: 'Processing' },
    //     2: { text: '已上线', status: 'Success' },
    //     3: { text: '异常', status: 'Error' },
    //   },
    // },
    // {
    //   title: '上次调度时间',
    //   dataIndex: 'updatedAt',
    //   sorter: true,
    //   valueType: 'dateTime',
    //   hideInForm: true,
    // },
    // {
    //   title: '操作',
    //   dataIndex: 'option',
    //   valueType: 'option',
    //   render: (_, record) => (
    //     <>
    //       <a
    //         onClick={() => {
    //           handleUpdateModalVisible(true);
    //           setStepFormValues(record);
    //         }}
    //       >
    //         配置
    //       </a>
    //       <Divider type="vertical" />
    //       <a href="">订阅警报</a>
    //     </>
    //   ),
    // },
  ];

  return (
    <PageHeaderWrapper>
      <ProTable<UserInfo>
        headerTitle="用户管理列表"
        actionRef={actionRef}
        rowKey="id"
        onChange={(_, _filter, _sorter) => {
          const sorterResult = _sorter as SorterResult<UserInfo>;
          if (sorterResult.field) {
            setSorter(`${sorterResult.field}_${sorterResult.order}`);
          }
        }}
        params={{
          sorter,
        }}
        toolBarRender={(action, { selectedRows }) => [
          <Button type="primary" onClick={() => handleModalVisible(true)}>
            <PlusOutlined /> 新建
          </Button>,
          selectedRows && selectedRows.length > 0 && (
            <Dropdown
              overlay={
                <Menu
                  onClick={async e => {
                    if (e.key === 'remove') {
                      await handleRemove(selectedRows);
                      action.reload();
                    }
                  }}
                  selectedKeys={[]}
                >
                  <Menu.Item key="remove">批量删除</Menu.Item>
                  <Menu.Item key="approval">批量审批</Menu.Item>
                </Menu>
              }
            >
              <Button>
                批量操作 <DownOutlined />
              </Button>
            </Dropdown>
          ),
        ]}
        tableAlertRender={(selectedRowKeys, selectedRows) => (
          <div>
            已选择 <a style={{ fontWeight: 600 }}>{selectedRowKeys.length}</a> 项&nbsp;&nbsp;
            {/*<span>*/}
            {/*  服务调用次数总计 {selectedRows.reduce((pre, item) => pre + item.callNo, 0)} 万*/}
            {/*</span>*/}
          </div>
        )}
        dataSource={userList?.userList}
        columns={columns}
        onLoad={(data) => {
          console.log(data,'123',userList?.userList);

        }}
        rowSelection={{}}
      />
      <CreateForm onCancel={() => handleModalVisible(false)} modalVisible={createModalVisible}>
        <ProTable<UserInfo, UserInfo>
          onSubmit={async value => {
            const success = await handleAdd(value);
            if (success) {
              handleModalVisible(false);
              if (actionRef.current) {
                actionRef.current.reload();
              }
            }
          }}
          rowKey="key"
          type="form"
          columns={columns}
          rowSelection={{}}
        />
      </CreateForm>
      {stepFormValues && Object.keys(stepFormValues).length ? (
        <UpdateForm
          onSubmit={async value => {
            const success = await handleUpdate(value);
            if (success) {
              handleModalVisible(false);
              setStepFormValues({});
              if (actionRef.current) {
                actionRef.current.reload();
              }
            }
          }}
          onCancel={() => {
            handleUpdateModalVisible(false);
            setStepFormValues({});
          }}
          updateModalVisible={updateModalVisible}
          values={stepFormValues}
        />
      ) : null}
    </PageHeaderWrapper>
  );
};

export default connect(({ userManager, loading }: ConnectState) => ({
  userList: userManager,
  submitting: loading.effects['userManager/getUser'],
}))(TableList);
