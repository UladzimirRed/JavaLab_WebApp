package com.epam.lab.model;

public class User extends AbstractEntity {
    private String userName;
    private String userSurname;
    private String login;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        if (!userSurname.equals(user.userSurname)) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + userSurname.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("user id=").append(super.getId());
        sb.append(", user name='").append(userName).append('\'');
        sb.append(", user surname='").append(userSurname).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
