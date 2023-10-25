package com.example.memberserver.infrastructure.IdProvier.firebase;

import com.example.memberserver.infrastructure.IdProvier.IdpClient;
import com.example.memberserver.member.Member;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class FirebaseAuthClient implements IdpClient {

    @Override
    public Long createMember(Member member) {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setUid(member.getId().toString());
        createRequest.setEmail(member.getEmail());
        createRequest.setPassword(member.getPassword());
        try {
            String uid = FirebaseAuth.getInstance().createUser(createRequest).getUid();
            return Long.valueOf(uid);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long updateMember(Member member) {
        try {
            FirebaseAuth.getInstance().setCustomUserClaims(member.getId().toString(),
                    new HashMap<>() {{
                        put("permissions", new ArrayList<>(member.getPermissions()));
                    }});
            return member.getId();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteMember(Member member) {
        try {
            FirebaseAuth.getInstance().deleteUser(member.getId().toString());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }
}
