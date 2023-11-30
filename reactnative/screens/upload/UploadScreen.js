import React, {useEffect, useState} from 'react';
import {
  Platform,
  Pressable,
  ScrollView,
  Text,
  TextInput,
  View,
  Image,
  StyleSheet,
} from 'react-native';
import {launchImageLibrary} from 'react-native-image-picker';
import DateTimePickerModal from 'react-native-modal-datetime-picker';
import Icon from 'react-native-vector-icons/MaterialIcons';
import {format} from 'date-fns';
import {ko} from 'date-fns/locale';

function UploadScreen({navigation, route}) {
  const [visibleTimePickerModal, setVisibleTimePickerModal] = useState(false);
  const [form, setForm] = useState({
    title: '',
    description: '',
    date: new Date(),
    location: '',
    photos: [],
  });

  useEffect(() => {
    console.log('form is detected');
    console.log(form.title);
    console.log(form.description);
    console.log(form.date);
    console.log(form.location);
    console.log(form.photos.map(item => item.uri));
  }, [form]);

  useEffect(() => {
    setForm({...form, location: route.params?.location});
  }, [route.params?.location]);

  const onSelectImage = () => {
    launchImageLibrary(
      {
        mediaType: 'photo',
        maxWidth: 512,
        maxHeight: 512,
        includeBase64: Platform.OS === 'android',
        selectionLimit: 0,
      },
      response => {
        if (response.didCancel) {
          return;
        }
        setForm({...form, photos: response.assets});
      },
    );
  };

  return (
    <View>
      <ScrollView>
        <TextInput
          placeholder="제목을 적어주세요."
          value={form.title}
          onChangeText={title => {
            setForm({...form, title});
          }}
        />
        <TextInput
          placeholder="자세한 설명을 적어주세요."
          value={form.description}
          onChangeText={description => {
            setForm({...form, description});
          }}
          multiline={true}
        />
        <View>
          <Text>{'날짜: ' + format(form.date, 'PPP', {locale: ko})}</Text>
        </View>
        <View>
          {form.photos?.map((item, index) => (
            <Image style={styles.img} source={{uri: item.uri}} />
          ))}
        </View>
      </ScrollView>
      <View>
        <Pressable onPress={() => setVisibleTimePickerModal(true)}>
          <Text>시간</Text>
          <Icon name="access-time" color="black" size={24} />
        </Pressable>
        <DateTimePickerModal
          isVisible={visibleTimePickerModal}
          mode="datetime"
          date={form.date}
          onConfirm={date => setForm({...form, date})}
          onCancel={() => setVisibleTimePickerModal(false)}
        />
        <Pressable onPress={() => navigation.push('LocationInputScreen')}>
          <Text>위치</Text>
          <Icon name="location-pin" color="black" size={24} />
        </Pressable>
        <Pressable onPress={onSelectImage}>
          <Text>사진</Text>
          <Icon name="image" color="black" size={24} />
        </Pressable>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  img: {
    backgroundColor: '#cdcdcd',
    borderRadius: 20,
    width: 100,
    height: 100,
  },
  spinner: {
    marginTop: 48,
    height: 104,
  },
});

export default UploadScreen;
