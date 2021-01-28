
package com.example.senfit;

import androidx.lifecycle.ViewModel;

public class MemberSignUpViewModel extends ViewModel {

    private Member member;

    public MemberSignUpViewModel(){
        this.member = new Member();
    }

    public Member getMember(){
        return this.member;
    }

}
