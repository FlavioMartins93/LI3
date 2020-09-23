#include <stdlib.h>
#include <string.h>
#include "post.h"
#include "date.h"

struct post {
	char* id;
	char* postTypeId; // 1 - questao; 2 - resposta
	char* parentId; // apenas para respostas
	char* acceptedAnswerId; // apenas para perguntas
	Date creationDate;
	int score;
	int viewCount;
	char* body;
	char* ownerUserId;
	char* lastEditorUserId;
	char* lastEditorDisplayName;
	Date lastEditDate;
	Date communityOwnedDate;
	Date closedDate;
	char* title;
	char* tags; // char*??
	int answerCount;
	int commentCount;
	int favoriteCount;
};

POST iniciaPost(){
	POST p = malloc(sizeof(struct post));

	p->id = NULL;
	p->postTypeId = NULL;
	p->parentId = NULL;
	p->acceptedAnswerId = NULL;
	p->creationDate = createDate(0, 0, 0);
	p->score = -1;
	p->viewCount = -1;
	p->body = NULL;
	p->ownerUserId = NULL;
	p->lastEditorUserId = NULL;
	p->lastEditorDisplayName = NULL;
	p->lastEditDate = createDate(0, 0, 0);
	p->communityOwnedDate = createDate(0, 0, 0);
	p->closedDate = createDate(0, 0, 0);
	p->title = NULL;
	p->tags = NULL;
	p->answerCount = -1;
	p->commentCount = -1;
	p->favoriteCount = -1;
}

POST setPostId(POST p, char* id){
	p->id = malloc(strlen(id) + 1);
	strcpy(p->id, id);	
	return p;
}

POST setPostTypeId(POST p, char* postTypeId){
	p->postTypeId = malloc(strlen(postTypeId) + 1);
	strcpy(p->postTypeId, postTypeId);
	return p;
}

POST setPostParentId(POST p, char* setPostParentId){
	p->parentId = malloc(strlen(setPostParentId) + 1);
	strcpy(p->parentId, setPostParentId);
	return p;
}

POST setPostAcceptedAnswerId(POST p, char* acceptedAnswerId){
	p->acceptedAnswerId = malloc(strlen(acceptedAnswerId) + 1);
	strcpy(p->acceptedAnswerId, acceptedAnswerId);
	return p;
}

POST setCreationDate(POST p, int dia, int mes, int ano){
	p->creationDate = createDate(dia, mes, ano);
	return p;
}

POST setPostScore(POST p, int score){
	p->score = score;
	return p;
}

POST setPostViewCount(POST p, int viewCount){
	p->viewCount = viewCount;
	return p;
}

POST setPostBody(POST p, char* body){
	p->body = malloc(strlen(body) + 1);
	strcpy(p->body, body);
	return p;
}

POST setPostOwnerUserId(POST p, char* ownerUserId){
	p->ownerUserId = malloc(strlen(ownerUserId) + 1);
	strcpy(p->ownerUserId, ownerUserId);
	return p;	
}

POST setPostLastEditorUserId(POST p, char* lastEditorUserId){
	p->lastEditorUserId = malloc(strlen(lastEditorUserId) + 1);
	strcpy(p->lastEditorUserId, lastEditorUserId);
	return p;
}

POST setPostLasteEditDate(POST p, int dia, int mes, int ano){
	p->lastEditDate = createDate(dia, mes, ano);
	return p;
}

POST setPostCommunityOwnedDate(POST p, int dia, int mes, int ano){
	p->communityOwnedDate = createDate(dia, mes, ano);
	return p;
}

POST setPostClosedDate(POST p, int dia, int mes, int ano){
	p->closedDate = createDate(dia, mes, ano);
	return p;
}

POST setPostTitle(POST p, char* title){
	p->title = malloc(strlen(title) + 1);
	strcpy(p->title, title);
	return p;
}

POST setPostTags(POST p, char* tags){
	p->tags = malloc(strlen(tags) + 1);
	strcpy(p->tags, tags);
	return p;
}

POST setPostAnswerCount(POST p, int answerCount){
	p->answerCount = answerCount;
	return p;
}

POST setPostCommentCount(POST p, int commentCount){
	p->commentCount = commentCount;
	return p;
}

POST setPostFavoriteCount(POST p, int favoriteCount){
	p->favoriteCount = favoriteCount;
	return p;
}
