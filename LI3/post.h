#ifndef __POST_H__
#define __POST_H__

typedef struct post *POST;

POST iniciaPost();
POST setPostId(POST p, char* id);
POST setPostTypeId(POST p, char* postTypeId);
POST setPostParentId(POST p, char* setPostParentId);
POST setPostAcceptedAnswerId(POST p, char* acceptedAnswerId);
POST setCreationDate(POST p, int dia, int mes, int ano);
POST setPostScore(POST p, int score);
POST setPostViewCount(POST p, int viewCount);
POST setPostBody(POST p, char* body);
POST setPostOwnerUserId(POST p, char* ownerUserId);
POST setPostLastEditorUserId(POST p, char* lastEditorUserId);
POST setPostLasteEditDate(POST p, int dia, int mes, int ano);
POST setPostCommunityOwnedDate(POST p, int dia, int mes, int ano);
POST setPostClosedDate(POST p, int dia, int mes, int ano);
POST setPostTitle(POST p, char* title);
POST setPostTags(POST p, char* tags);
POST setPostAnswerCount(POST p, int answerCount);
POST setPostCommentCount(POST p, int commentCount);
POST setPostFavoriteCount(POST p, int favoriteCount);

#endif