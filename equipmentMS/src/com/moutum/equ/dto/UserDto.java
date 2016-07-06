package com.moutum.equ.dto;
/************************************************************************************
 * @Title        : UserDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月10日 下午12:26:24
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class UserDto
{
    private long userId;//编号
    
    private String loginAccount;//登陆账号
    
    private String loginPassword;//登录密码
    
    private String userName;//用户姓名
    
    private String userPhone;//用户手机号码
    
    private int isActivated;//账号是否激活
    
    private String email;//电子邮箱
    
    private String roleName;//角色ID

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getLoginAccount()
    {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount)
    {
        this.loginAccount = loginAccount;
    }

    public String getLoginPassword()
    {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword)
    {
        this.loginPassword = loginPassword;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public int getIsActivated()
    {
        return isActivated;
    }

    public void setIsActivated(int isActivated)
    {
        this.isActivated = isActivated;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
}