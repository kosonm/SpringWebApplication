package com.marcin.service;

import com.marcin.model.User;

// Interjefs z metodami
// Rozdzielenie warstw
public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
