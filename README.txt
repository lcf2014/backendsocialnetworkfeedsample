# Backend Sample Social Network

## Goals

This project is a social network Feed API that allows users to like a post, to create a post, to share a post,
to quote a post. It also provides for the user the profile of a user,  all user posts or all user followings posts.

## Technologies

- Java 11
- SpringBoot 2.6.2
- OpenFeign
- MongoDB


## Suggestions of future work

We could add cache as Redis to get users details. Also, add cache to find the posts.
When a user have many followings it can get slow to retrieve all the posts consulting a database. So we should implement something to deal
with that. For example, we could use a cache to save all the posts with an expiration policy, since we could not save posts forever
on cache. We could add only most recents post on cache.

## Sample

This is just a sample of a backend social network to study more OpenFeign, SpringBoot and MongoDB.
The controller does not offer best practices, since I´ve simplified and created only one controller for the application.
I should create one controller for each subject.

