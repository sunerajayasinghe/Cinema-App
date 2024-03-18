"use client";

import { Button, Form, Input, Row, Col, Tabs } from 'antd';

const { TabPane } = Tabs;

type LoginType = {
  emailAdress: string;
  password: string;
};

type SignUpType = {
  emailAdress: string;
  password: string;
  mobileNumber: string;
};

const loginFinish = (values: LoginType) => {
  console.log('Login Success:', values);
};

const loginFinishFailed = (errorInfo: any) => {
  console.log('Login Failed:', errorInfo);
};

const signupFinish = (values: SignUpType) => {
  console.log('Signup Success:', values);
};

const signupFinishFailed = (errorInfo: any) => {
  console.log('Signup Failed:', errorInfo);
};

const App: React.FC = () => (
  <Row justify="center" align="middle" style={{ minHeight: '100vh' }}>
    <Col span={12}>
      <Tabs defaultActiveKey="login">
        <TabPane tab="Login" key="login">
          <Form
            name="login"
            initialValues={{ remember: true }}
            style={{ maxWidth: '80vh' }}
            onFinish={loginFinish}
            onFinishFailed={loginFinishFailed}
            autoComplete="off"
          >
            <Form.Item<LoginType>
              label="Email Address"
              name="emailAdress"
              rules={[{ required: true, message: 'Please input your email address!' }]}
            >
              <Input />
            </Form.Item>

            <Form.Item<LoginType>
              label="Password"
              name="password"
              rules={[{ required: true, message: 'Please input your password!' }]}
            >
              <Input.Password />
            </Form.Item>

            <Form.Item>
              <Button type="primary" htmlType="submit">
                Login
              </Button>
            </Form.Item>
          </Form>
        </TabPane>
        <TabPane tab="Sign Up" key="signup">
          <Form
            name="signUp"
            initialValues={{ remember: true }}
            style={{ maxWidth: '80vh' }}
            onFinish={loginFinish}
            onFinishFailed={loginFinishFailed}
            autoComplete="off"
          >
            <Form.Item<SignUpType>
              label="Email Address"
              name="emailAdress"
              rules={[{ required: true, message: 'Please input your email address!' }]}
            >
              <Input />
            </Form.Item>

            <Form.Item<SignUpType>
              label="Mobile Number"
              name="mobileNumber"
              rules={[{ required: true, message: 'Please input your email address!' }]}
            >
              <Input />
            </Form.Item>

            <Form.Item<SignUpType>
              label="Password"
              name="password"
              rules={[{ required: true, message: 'Please input your password!' }]}
            >
              <Input.Password />
            </Form.Item>

            <Form.Item>
              <Button type="primary" htmlType="submit">
                Sign Up
              </Button>
            </Form.Item>
          </Form>
        </TabPane>
      </Tabs>
    </Col>
  </Row>
);

export default App;
