package com.Simple.Firebase.VeriTabani;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VeriTabani {

    private FirebaseAuth auth;
    private FirebaseUser currentuser;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private static VeriTabani veritabani = null;
    private String userID;
    private StorageReference fbstorage;

    private VeriTabani() {
    }

    //firebase auth a erişim tanımlaması
    public FirebaseAuth getAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    //uygulamaya eposta adresi ve şifre ile giriş yapmış kullanıcıyı getirir --aktif kullanıcı--
    public FirebaseUser getCurrentuser() {
        currentuser = getAuth().getCurrentUser();
        return currentuser;
    }

    public FirebaseDatabase getDb() {
        db = FirebaseDatabase.getInstance();
        return db;
    }

    //parametre aldığı referansa erişim sağlar
    public DatabaseReference getDbref(String referans) {
        dbref = getDb().getReference(referans);
        dbref.keepSynced(true);
        return dbref;
    }

    //singleton örneği ile sadece tek nesne oluşturulur
    public static VeriTabani getVeritabani() {
        if (veritabani == null) {
            veritabani = new VeriTabani();
        }
        return veritabani;
    }

    //aktif kullanıcının uniqe id' sini getirir
    public String getUserID() {
        if(getCurrentuser()!=null){
            userID = getCurrentuser().getUid();
        }
        return userID;
    }

    //eğer resim vb. öğeler tutulacaksa bu storage referansıyla eklenir.
    public StorageReference getFbstorage() {
        fbstorage = FirebaseStorage.getInstance().getReference();
        return fbstorage;
    }

}
