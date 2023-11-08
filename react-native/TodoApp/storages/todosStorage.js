import React from 'react';
import AsyncStorage from '@react-native-community/async-storage';

const key = 'todos';

const todosStorage = {
  async get() {
    try {
      const rawTodos = await AsyncStorage.getItem(key);
      console.log('here is get()');
      console.log(rawTodos);

      if (!rawTodos) {
        throw new Error('No saved todos');
      }

      const savedTodos = JSON.parse(rawTodos);
      return savedTodos;
    } catch (e) {}
  },

  async set(data) {
    try {
      await AsyncStorage.setItem(key, JSON.stringify(data));
      console.log('here is get()');
      console.log(data);
    } catch (e) {
      throw new Error('Failed to load todos');
    }
  },
};

export default todosStorage;
