import React, {useState} from 'react';
import {Pressable, ScrollView, StyleSheet, Text, View} from 'react-native';
import Avatar from '../components/Avatar';
import HomeIcon from '../components/HomeIcon';
import OvalButton from '../components/OvalButton';
import SettingIcon from '../components/SettingIcon';
import StudyReportCard from '../components/StudyReportCard';
import BookmarkCard from '../components/BookmarkCard';

function ProfileScreen() {
  const profile = {
    firstName: 'Anastasia',
    lastName: 'Smith',
  };

  const [activeStudyReportsButton, setActiveStudyReportsButton] =
    useState(true);

  const studyReports = [
    {title: 'Set #1', date: 'Sep 13-19', pts: 30},
    {title: 'Set #2', date: 'Sep 13-18', pts: 29},
    {title: 'Set #3', date: 'Sep 13-17', pts: 28},
    {title: 'Set #4', date: 'Sep 13-16', pts: 27},
    {title: 'Set #5', date: 'Sep 13-15', pts: 25},
    {title: 'Set #6', date: 'Sep 13-14', pts: 26},
  ];

  const bookmarks = [
    {title: 'The triangle above has an area 1', content: 'Set #1 - Reading'},
    {title: 'The triangle above has an area 2', content: 'Set #2 - Reading'},
    {title: 'The triangle above has an area 3', content: 'Set #3 - Reading'},
    {title: 'The triangle above has an area 4', content: 'Set #4 - Reading'},
    {title: 'The triangle above has an area 5', content: 'Set #5 - Reading'},
    {title: 'The triangle above has an area 6', content: 'Set #6 - Reading'},
  ];

  const sayHello = () => {
    console.log('Hello');
  };

  return (
    <ScrollView style={styles.root}>
      <View style={styles.head}>
        <HomeIcon onPress={sayHello} />
        <Avatar style={styles.avatar} size={64} />
        <SettingIcon onPress={sayHello} />
      </View>
      <View style={styles.nameContainer}>
        <Text style={styles.nameText}>{profile.firstName}</Text>
        <Text style={styles.nameText}>{profile.lastName}</Text>
      </View>
      <View style={styles.editButtonContainer}>
        <Pressable style={styles.editButton} onPress={sayHello}>
          <Text style={styles.editButtonText}>Edit</Text>
        </Pressable>
      </View>
      <View style={styles.ovalButtonContainer}>
        <OvalButton
          active={activeStudyReportsButton}
          name="Study Reports"
          icon={require('../assets/study_reports_active.png')}
          onPress={() => {
            setActiveStudyReportsButton(true);
          }}
        />
        <OvalButton
          active={!activeStudyReportsButton}
          name="Bookmarks"
          icon={require('../assets/section_bookmarks.png')}
          onPress={() => {
            setActiveStudyReportsButton(false);
          }}
        />
      </View>
      <View style={styles.cardContainter}>
        {activeStudyReportsButton
          ? studyReports.map((item, index) => (
              <StudyReportCard
                title={item.title}
                date={item.date}
                pts={item.pts}
              />
            ))
          : bookmarks.map((item, index) => (
              <BookmarkCard title={item.title} content={item.content} />
            ))}
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
  ovalButtonContainer: {
    // backgroundColor: '#44e50ac2',
    height: 88,
    marginTop: 16,
    marginHorizontal: 48,
    flexDirection: 'row',
    alignItems: 'flex-start',
    justifyContent: 'space-between',
  },
  cardContainter: {
    // backgroundColor: '#44e50ac2',
    marginTop: 40,
    marginHorizontal: 28,
  },
});

export default ProfileScreen;
