#ifndef __UTILIZADOR_H__
#define __UTILIZADOR_H__

typedef struct utilizador *UTILIZADOR;

UTILIZADOR iniciaUtilizador();
UTILIZADOR setUtId(UTILIZADOR u, char* id);
UTILIZADOR setUtRep(UTILIZADOR u, int reputation);
UTILIZADOR setUtCreationDate(UTILIZADOR u, int dia, int mes, int ano);
UTILIZADOR setUtDisplayName(UTILIZADOR u, char* displayName);
UTILIZADOR setUtEmailHash(UTILIZADOR u, char* emailHash);
UTILIZADOR setUtLastAccessDate(UTILIZADOR u, int dia, int mes, int ano);
UTILIZADOR setWebSiteUrl(UTILIZADOR u, char* webSiteUrl);
UTILIZADOR setUtLocation(UTILIZADOR u, char* location);
UTILIZADOR setUtAge(UTILIZADOR u, int age);
UTILIZADOR setUtAboutMe(UTILIZADOR u, char* aboutMe);
UTILIZADOR setUtViews(UTILIZADOR u, int views);
UTILIZADOR setUtUpVotes(UTILIZADOR u, int upVotes);
UTILIZADOR setUtDownVotes(UTILIZADOR u, int downVotes);

// setPosts
// addPost(u, (Date,postID)) ???

UTILIZADOR setTotalPosts(UTILIZADOR u, int totalPosts);
UTILIZADOR incTotalPosts(UTILIZADOR u);

char* getUtId(UTILIZADOR u);
int getUtRep(UTILIZADOR u);
Date getUtCreationDate(UTILIZADOR u);
char* getUtDisplayName(UTILIZADOR u);
char* getUtEmailHash(UTILIZADOR u);
Date getUtLastAccessDate(UTILIZADOR u);
char* getWebSiteUrl(UTILIZADOR u);
char* getUtLocation(UTILIZADOR u);
int getUtAge(UTILIZADOR u);
char* getUtAboutMe(UTILIZADOR u);
int getUtViews(UTILIZADOR u);
int getUtUpVotes(UTILIZADOR u);
int getUtDownVotes(UTILIZADOR u);

//getPosts

int getUtTotalPosts(UTILIZADOR u);

/* interrogacao 3
LList?? getPostsIntervaloTempo
interrogacao 4 get..+tags

interrogacao 5
char*?? getBios(Utilizador u){
	...
}
*/


#endif
