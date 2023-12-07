import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

function BookmarkCard({title, content}) {
  return (
    <View style={styles.card}>
      <View style={styles.cardTitle}>
        <Text style={styles.cardTitleText}>{title}</Text>
      </View>
      <View style={styles.cardContent}>
        <Text style={styles.cardContentText}>{content}</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
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
    flexDirection: 'row',
    alignItems: 'flex-start',
    justifyContent: 'space-between',
  },
  cardContentText: {
    fontSize: 12,
    color: '#AFAFAF',
  },
});

export default BookmarkCard;
