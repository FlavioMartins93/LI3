#include <stdlib.h>
#include <string.h>
#include "date.h"
#include "utilizador.h"

struct utilizador {
	char* id;
	int reputation;
	Date creationDate;
	char* displayName;
	char* emailHash; 
	Date lastAccessDate;
	char* webSiteUrl;
	char* location;
	int age;
	char* aboutMe;
	int views;
	int upVotes;
	int downVotes;
	// AVL (data, PostID) ????
	int totalPosts;
};

UTILIZADOR iniciaUtilizador(){
	UTILIZADOR u = malloc(sizeof(struct utilizador));

	u->id = NULL;
	u->reputation = 0;
	u->creationDate = createDate(0, 0, 0);
	u->displayName = NULL;
	u->emailHash = NULL;
	u->lastAccessDate = createDate(0, 0, 0);
	u->webSiteUrl = NULL;
	u->location = NULL;
	u->age = 0;
	u->aboutMe = NULL;
	u->views = 0;
	u->upVotes = 0;
	u->downVotes = 0;
	//u->posts = NULL;
	u->totalPosts = 0;
}

UTILIZADOR setUtId(UTILIZADOR u, char* id){
	u->id = malloc(strlen(id) + 1);
	strcpy(u->id, id);
	return u;
}

UTILIZADOR setUtRep(UTILIZADOR u, int reputation){
	u->reputation = reputation;
	return u;
}

UTILIZADOR setUtCreationDate(UTILIZADOR u, int dia, int mes, int ano){
	u->creationDate = createDate(dia, mes, ano);
	return u;
}

UTILIZADOR setUtDisplayName(UTILIZADOR u, char* displayName){
	u->displayName = malloc(strlen(displayName) + 1);
	strcpy(u->displayName, displayName);
	return u;
}

UTILIZADOR setUtEmailHash(UTILIZADOR u, char* emailHash){
	u->emailHash = malloc(strlen(emailHash) + 1);
	strcpy(u->emailHash, emailHash);
	return u;
}

UTILIZADOR setUtLastAccessDate(UTILIZADOR u, int dia, int mes, int ano){
	u->lastAccessDate = createDate(dia, mes, ano);
	return u;
}

UTILIZADOR setWebSiteUrl(UTILIZADOR u, char* webSiteUrl){
	u->webSiteUrl = malloc(strlen(webSiteUrl) + 1);
	strcpy(u->webSiteUrl, webSiteUrl);
	return u;
}

UTILIZADOR setUtLocation(UTILIZADOR u, char* location){
	u->location = malloc(strlen(location) + 1);
	strcpy(u->location, location);
	return u;
}

UTILIZADOR setUtAge(UTILIZADOR u, int age){
	u->age = age;
	return u;
}

UTILIZADOR setUtAboutMe(UTILIZADOR u, char* aboutMe){
	u->aboutMe = malloc(strlen(aboutMe) + 1);
	strcpy(u->aboutMe, aboutMe);
	return u;
}

UTILIZADOR setUtViews(UTILIZADOR u, int views){
	u->views = views;
	return u;
}

UTILIZADOR setUtUpVotes(UTILIZADOR u, int upVotes){
	u->upVotes = upVotes;
	return u;
}

UTILIZADOR setUtDownVotes(UTILIZADOR u, int downVotes){
	u->downVotes = downVotes;
	return u;
}

// setPosts
// addPost(u, (Date,postID)) ???

UTILIZADOR setTotalPosts(UTILIZADOR u, int totalPosts){
	u->totalPosts = totalPosts;
	return u;
}

UTILIZADOR incTotalPosts(UTILIZADOR u){
	u->totalPosts += 1;
	return u;
}

//interrogacao 1
char* getUtId(UTILIZADOR u){
	char* id = malloc(strlen(u->id) + 1);
	strcpy(id, u->id);
	return id;
}

int getUtRep(UTILIZADOR u){
	return u->reputation;
}

Date getUtCreationDate(UTILIZADOR u){
	return u->creationDate;
}

char* getUtDisplayName(UTILIZADOR u){
	return u->displayName;
}

char* getUtEmailHash(UTILIZADOR u){
	return u->emailHash;
}

Date getUtLastAccessDate(UTILIZADOR u){
	return u->lastAccessDate;
}

char* getWebSiteUrl(UTILIZADOR u){
	return u->webSiteUrl;
}

char* getUtLocation(UTILIZADOR u){
	return u->location;
}

int getUtAge(UTILIZADOR u){
	return u->age;
}

char* getUtAboutMe(UTILIZADOR u){
	return u->aboutMe;
}

int getUtViews(UTILIZADOR u){
	return u->views;
}

int getUtUpVotes(UTILIZADOR u){
	return u->upVotes;
}

int getUtDownVotes(UTILIZADOR u){
	return u->downVotes;
}



//interrogacao 2
int getUtTotalPosts(UTILIZADOR u){
	return u->totalPosts;
}

/* interrogacao 3
LList?? getPostsIntervaloTempo
interrogacao 4 get..+tags

interrogacao 5
char*?? getBios(Utilizador u){
	...
}
*/

