import React from 'react';
import {
  Image,
  ImageBackground,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import Avatar from '../components/Avatar';

function ProfileScreen() {
  const onPress = () => {
    console.log('Hello');
  };

  return (
    <ScrollView style={styles.root}>
      <View style={styles.head}>
        <Pressable style={styles.home} onPress={onPress}>
          <Image source={require('../assets/home.png')} size={24} />
        </Pressable>
        <Avatar style={styles.avatar} size={64} />
        <Pressable hitSlop={8} onPress={onPress}>
          <Image source={require('../assets/setting.png')} size={24} />
        </Pressable>
      </View>
      <View style={styles.nameContainer}>
        <Text style={styles.nameText}>Anastasia</Text>
        <Text style={styles.nameText}>Smith</Text>
      </View>
      <View style={styles.editButtonContainer}>
        <Pressable style={styles.editButton} onPress={onPress}>
          <Text style={styles.editButtonText}>Edit</Text>
        </Pressable>
      </View>
      <View style={styles.studyReportsBookmarksButtonsContainer}>
        <View style={styles.studyReportsContainer}>
          <ImageBackground
            source={require('../assets/oval_copy.png')}
            resizeMode="cover"
            style={styles.studyReportsImageBackground}>
            <Image
              source={require('../assets/study_reports_active.png')}
              style={styles.studyReportsIcon}
            />
            <Text style={styles.studyReportsText}>Study Reports</Text>
          </ImageBackground>
        </View>
        <View style={styles.bookmarksContainer}>
          <Image
            source={require('../assets/section_bookmarks.png')}
            style={styles.bookmarksIcon}
          />
          <Text style={styles.bookmarksText}>Bookmarks</Text>
        </View>
      </View>
      <View style={styles.cardContainter}>
        <View style={styles.card}>
          <View style={styles.cardTitle}>
            <Text style={styles.cardTitleText}>Set #2</Text>
          </View>
          <View style={styles.cardContent}>
            <Text style={styles.cardContentText}>Sep 13-19</Text>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.cardTitle}>
            <Text style={styles.cardTitleText}>Set #2</Text>
          </View>
          <View style={styles.cardContent}>
            <Text style={styles.cardContentText}>Sep 13-19</Text>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.cardTitle}>
            <Text style={styles.cardTitleText}>Set #2</Text>
          </View>
          <View style={styles.cardContent}>
            <Text style={styles.cardContentText}>Sep 13-19</Text>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.cardTitle}>
            <Text style={styles.cardTitleText}>Set #2</Text>
          </View>
          <View style={styles.cardContent}>
            <Text style={styles.cardContentText}>Sep 13-19</Text>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.cardTitle}>
            <Text style={styles.cardTitleText}>Set #2</Text>
          </View>
          <View style={styles.cardContent}>
            <Text style={styles.cardContentText}>Sep 13-19</Text>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.cardTitle}>
            <Text style={styles.cardTitleText}>Set #2</Text>
          </View>
          <View style={styles.cardContent}>
            <Text style={styles.cardContentText}>Sep 13-19</Text>
          </View>
        </View>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  root: {
    backgroundColor: '#FFF',
    flex: 1,
  },
  head: {
    // backgroundColor: '#44e50ac2',
    height: 72,
    flexDirection: 'row',
    marginTop: 62,
    marginHorizontal: 28,
    alignItems: 'flex-start',
    justifyContent: 'space-between',
  },
  avatar: {
    marginTop: 8,
  },
  nameContainer: {
    // backgroundColor: '#44e50ac2',
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 16,
    marginHorizontal: 40,
    height: 44,
  },
  nameText: {
    color: '#333333',
    fontSize: 20,
    lineHeight: 28,
  },
  editButtonContainer: {
    // backgroundColor: '#44e50ac2',
    marginTop: 8,
    marginHorizontal: 68,
    height: 16,
  },
  editButton: {
    width: '100%',
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  editButtonText: {
    fontSize: 14,
    color: '#333333',
  },
  studyReportsBookmarksButtonsContainer: {
    // backgroundColor: '#44e50ac2',
    height: 88,
    marginTop: 16,
    marginHorizontal: 48,
    flexDirection: 'row',
    alignItems: 'flex-start',
    justifyContent: 'space-between',
  },
  studyReportsContainer: {
    // backgroundColor: '#aae50ac2',
    width: 120,
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  studyReportsImageBackground: {
    width: 88,
    height: 88,
    alignItems: 'center',
    justifyContent: 'center',
  },
  studyReportsIcon: {
    marginTop: 24,
  },
  studyReportsText: {
    width: 120,
    height: 22,
    fontSize: 16,
    textAlign: 'center',
    marginTop: 4,
    color: '#333333',
  },
  bookmarksContainer: {
    // backgroundColor: '#aae50ac2',
    width: 120,
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  bookmarksIcon: {
    marginTop: 24,
  },
  bookmarksText: {
    width: 120,
    height: 22,
    fontSize: 16,
    textAlign: 'center',
    marginTop: 4,
    color: '#333333',
    opacity: 0.3,
  },
  cardContainter: {
    // backgroundColor: '#44e50ac2',
    marginTop: 40,
    marginHorizontal: 28,
  },
  card: {
    backgroundColor: '#F7F7F7',
    marginBottom: 12,
    height: 88,
    borderRadius: 16,
    alignItems: 'center',
  },
  cardTitle: {
    // backgroundColor: '#aae50ac2',
    height: 22,
    width: 264,
    marginTop: 20,
    marginHorizontal: 20,
  },
  cardTitleText: {
    fontSize: 16,
    color: '#333',
  },
  cardContent: {
    // backgroundColor: '#aae50ac2',
    width: 264,
    height: 22,
    marginTop: 8,
    fontSize: 12,
  },
  cardContentText: {
    fontSize: 12,
    color: '#AFAFAF',
  },
});

export default ProfileScreen;
