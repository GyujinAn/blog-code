import React, {useState} from 'react';
import {TextInput, View, Image, TouchableOpacity, Keyboard} from 'react-native';
import {StyleSheet} from 'react-native';

function AddTodo({onInsert}) {
  const [text, setText] = useState();

  const onPress = () => {
    onInsert(text);
    setText('');
    Keyboard.dismiss();
  };

  const button = (
    <View style={styles.buttonStyle}>
      <Image source={require('../assets/icons/add_white/add_white.png')} />
    </View>
  );

  return (
    <View style={styles.block}>
      <TextInput
        placeholder="put your task"
        style={styles.input}
        value={text}
        onChangeText={setText}
        onSubmitEditing={onPress}
        returnKeyType="done"
      />
      <TouchableOpacity activeOpacity={0.5} onPress={onPress}>
        {button}
      </TouchableOpacity>
    </View>
  );
}
const styles = StyleSheet.create({
  block: {
    height: 64,
    paddingHorizontal: 16,
    borderColor: '#bdbdbd',
    borderTopWidth: 1,
    borderBottomWidth: 1,
    justifyContent: 'center',
  },
  input: {
    fontSize: 16,
    paddingVertical: 8,
  },
  buttonStyle: {
    alignItems: 'center',
    justifyContent: 'center',
    width: 48,
    height: 48,
    backgroundColor: '#26a69a',
    borderRedius: 24,
  },
});

export default AddTodo;
