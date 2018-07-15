// UserServer.aidl
package com.weiche.module_girls;
import com.weiche.module_girls.grils.User;

interface UserServer {
    List<User> getBookList();

    void addUserInOut(inout User user);
}
