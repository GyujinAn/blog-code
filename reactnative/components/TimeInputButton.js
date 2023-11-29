import React, {useState} from 'react';
import {Pressable, Text, View} from 'react-native';
import {DatePicker} from 'react-native-date-picker';
import Icon from 'react-native-vector-icons/MaterialIcons';
import DateTimePickerModal from 'react-native-modal-datetime-picker';

function TimeInputButton({time, onConfirm}) {
  const [visible, setVisible] = useState(false);

  return (
    <View>
      <Pressable onPress={() => setVisible(true)}>
        <Text>시간</Text>
        <Icon name="access-time" color="black" size={24} />
      </Pressable>
      <DateTimePickerModal
        isVisible={visible}
        mode="datetime"
        date={time}
        onConfirm={onConfirm}
        onCancel={() => setVisible(false)}
      />

      {/* <DatePicker
        modal
        open={open}
        date={time}
        onConfirm={selectedTime => {
          setOpen(false);
          onConfirm(selectedTime);
        }}
        onCancel={() => {
          setOpen(false);
        }}
      /> */}
    </View>
  );
}

export default TimeInputButton;
