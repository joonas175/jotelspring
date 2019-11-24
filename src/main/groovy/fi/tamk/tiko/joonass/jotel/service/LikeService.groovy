package fi.tamk.tiko.joonass.jotel.service

interface LikeService {

    boolean userHasVoted(int postId, int userId)

    int getVoteCount(int postId)

    int vote(int postId, int userId, boolean dislike)
}