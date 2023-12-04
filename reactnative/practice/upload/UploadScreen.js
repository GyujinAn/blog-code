import {format} from 'date-fns';
import {ko} from 'date-fns/locale';
import React, {useEffect, useState} from 'react';
import {
  Image,
  Platform,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  View,
} from 'react-native';
import {launchImageLibrary} from 'react-native-image-picker';
import DateTimePickerModal from 'react-native-modal-datetime-picker';
import Icon from 'react-native-vector-icons/MaterialIcons';
import IconRightButton from '../../components/IconRightButton';

function UploadScreen({navigation, route}) {
  const [visibleTimePickerModal, setVisibleTimePickerModal] = useState(false);
  const [form, setForm] = useState({
    title: '',
    description: '',
    date: new Date(),
    location: {
      place_id: '',
      description: '',
    },
    photos: [],
  });

  const onSubmit = () => {
    console.log('onSubmit is detected');
  };

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

  useEffect(() => {
    navigation.setOptions({
      headerRight: () => <IconRightButton onPress={onSubmit} name="send" />,
    });
  }, [navigation]);

  useEffect(() => {
    console.log('=== form is detected ===');
    console.log(`form.title: ${form.title}`);
    console.log(`form.description: ${form.title}`);
    console.log(`form.date: ${form.title}`);
    console.log(`form.location: ${form.title}`);
    console.log(`form.photos: ${form.photos.map(item => item.uri)}`);
  }, [form]);

  useEffect(() => {
    setForm({...form, location: route.params?.location});
  }, [route.params?.location]);

  return (
    <View style={styles.container}>
      <View style={styles.date}>
        <Text>{`날짜: ${format(form.date, 'yyyy-MM-dd HH:mm:ss', {
          locale: ko,
        })}`}</Text>
      </View>
      <View style={styles.location}>
        <Text>{`위치: ${form.location ? form.location.description : ''}`}</Text>
      </View>
      <TextInput
        style={styles.title}
        placeholder="제목을 적어주세요."
        value={form.title}
        onChangeText={title => {
          setForm({...form, title});
        }}
      />
      <ScrollView style={styles.description}>
        <TextInput
          style={styles.description}
          placeholder="자세한 설명을 적어주세요."
          value={form.description}
          onChangeText={description => {
            setForm({...form, description});
          }}
          multiline={true}
        />
        <View style={styles.imgContainer}>
          {form.photos?.map((item, index) => (
            <Image style={styles.img} source={{uri: item.uri}} />
          ))}
        </View>
      </ScrollView>
      <View style={styles.bottom}>
        <Pressable
          style={styles.button}
          onPress={() => {
            setVisibleTimePickerModal(true);
          }}>
          <Text>날짜</Text>
          <Icon name="access-time" color="black" size={24} />
        </Pressable>
        <DateTimePickerModal
          isVisible={visibleTimePickerModal}
          mode="datetime"
          date={form.date}
          onConfirm={date => setForm({...form, date})}
          onCancel={() => setVisibleTimePickerModal(false)}
        />
        <Pressable
          style={styles.button}
          onPress={() => navigation.push('LocationInputScreen')}>
          <Text>위치</Text>
          <Icon name="location-pin" color="black" size={24} />
        </Pressable>
        <Pressable style={styles.button} onPress={onSelectImage}>
          <Text>사진</Text>
          <Icon name="image" color="black" size={24} />
        </Pressable>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  date: {height: 30},
  location: {height: 30},
  title: {height: 50, fontSize: 20},
  description: {fontSize: 15},
  imgContainer: {
    flexDirection: 'row',
  },
  img: {
    borderRadius: 20,
    width: 75,
    height: 75,
    margin: 2,
  },
  bottom: {
    flex: 0,
    height: 60,
    flexDirection: 'row',
    position: 'fixed',
    bottom: 0,
    borderColor: '#bdbdbd',
    borderWidth: 1,
  },
  button: {
    padding: 5,
  },
  spinner: {
    marginTop: 48,
    height: 104,
  },
});

export default UploadScreen;
