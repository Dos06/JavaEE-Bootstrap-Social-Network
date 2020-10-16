package javaee.db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaee?useUnicode=true&serverTimezone=UTC",
                    "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    public static boolean addUser(User user) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (email, password, full_name, picture_url, birthdate) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPictureURL());
            preparedStatement.setDate(5, user.getSqlBirthdate());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addPost(Post post) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO posts (title, short_content, content, post_date, author_id) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getShortContent());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setTimestamp(4, post.getPostDate());
            preparedStatement.setInt(5, post.getUser().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addFriendRequest(FriendRequest friendRequest) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO friends_requests (sent_time, user_id, request_sender_id) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setTimestamp(1, friendRequest.getSentTime());
            preparedStatement.setInt(2, friendRequest.getUser().getId());
            preparedStatement.setInt(3, friendRequest.getFriend().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addChat(Chat chat) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO chats (created_date, latest_message_text, latest_message_time, user_id, opponent_user_id) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setTimestamp(1, chat.getCreatedDate());
            preparedStatement.setString(2, chat.getLatestMessageText());
            preparedStatement.setTimestamp(3, chat.getLatestMessageTime());
            preparedStatement.setInt(4, chat.getUser().getId());
            preparedStatement.setInt(5, chat.getOpponent().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addMessage(Message message) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO messages (message_text, read_by_receiver, sent_time, chat_id, user_id, opponent_user_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, message.getMessageText());

            int value = message.isReadByReceiver() ? 1 : 0;
            preparedStatement.setInt(2, value);

            preparedStatement.setTimestamp(3, message.getSentTime());
            preparedStatement.setInt(4, message.getChat().getId());
            preparedStatement.setInt(5, message.getUser().getId());
            preparedStatement.setInt(6, message.getOpponent().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addFriend(Friend friend) {
        int rows_added = 0;
        int rows_added1 = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO friends (added_time, user_id, friend_id) " +
                            "VALUES (?, ?, ?);"
            );
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "INSERT INTO friends (added_time, user_id, friend_id) " +
                            "VALUES (?, ?, ?);"
            );

            preparedStatement.setTimestamp(1, friend.getAddedTime());
            preparedStatement.setInt(2, friend.getUser().getId());
            preparedStatement.setInt(3, friend.getFriend().getId());

            preparedStatement1.setTimestamp(1, friend.getAddedTime());
            preparedStatement1.setInt(2, friend.getFriend().getId());
            preparedStatement1.setInt(3, friend.getUser().getId());

            rows_added = preparedStatement.executeUpdate();
            rows_added1 = preparedStatement1.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0 && rows_added1 > 0;
    }

    public static boolean addLanguage(Language language) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO languages (name, code) " +
                            "VALUES (?, ?)"
            );
            preparedStatement.setString(1, language.getName());
            preparedStatement.setString(2, language.getCode());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addPublication(Publication publication) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO publications (name, description, rating) " +
                            "VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setDouble(3, publication.getRating());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addNewsItem(NewsItem newsItem) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO news (title, short_content, content, picture_url, post_date, language_id, publication_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, newsItem.getTitle());
            preparedStatement.setString(2, newsItem.getShortContent());
            preparedStatement.setString(3, newsItem.getContent());
            preparedStatement.setString(4, newsItem.getPictureURL());
            preparedStatement.setTimestamp(5, newsItem.getPostDate());
            preparedStatement.setInt(6, newsItem.getLanguage().getId());
            preparedStatement.setInt(7, newsItem.getPublication().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static ArrayList<NewsItem> getPublicationNewsItems(int publicationId, int languageId) {
        ArrayList<NewsItem> publicationNewsItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id " +
                            "FROM news n " +
                            "INNER JOIN publications p ON n.publication_id = p.id " +
                            "INNER JOIN languages l ON n.language_id = l.id " +
                            "WHERE n.publication_id = ? AND n.language_id = ? "
            );
            preparedStatement.setInt(1, publicationId);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                publicationNewsItems.add(new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publicationNewsItems;
    }

    public static ArrayList<NewsItem> getNewsItemsByLanguage(int languageId) {
        ArrayList<NewsItem> publicationNewsItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id " +
                            "FROM news n " +
                            "INNER JOIN languages l ON n.language_id = l.id " +
                            "WHERE n.language_id = ? "
            );
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                publicationNewsItems.add(new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publicationNewsItems;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> getUsersByFullName(int userId, String fullName) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users " +
                            "WHERE full_name LIKE '%" + fullName + "%' AND id!=" + userId
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, author_id " +
                            "FROM posts " +
                            "ORDER BY post_date DESC"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getInt("author_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getPostsByUserID(int authorId) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, author_id " +
                            "FROM posts " +
                            "WHERE author_id=? " +
                            "ORDER BY post_date DESC"
            );
            preparedStatement.setInt(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getInt("author_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Friend> getFriendsByUserID(int userId) {
        ArrayList<Friend> friends = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, added_time, user_id, friend_id " +
                            "FROM friends " +
                            "WHERE user_id=? " +
                            "ORDER BY added_time DESC"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friends.add(new Friend(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("added_time"),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("friend_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return friends;
    }

    public static ArrayList<Chat> getChatsByUserID(int userId) {
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, created_date, latest_message_text, latest_message_time, user_id, opponent_user_id " +
                            "FROM chats " +
                            "WHERE user_id=? OR opponent_user_id=? " +
                            "ORDER BY latest_message_time DESC"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chats.add(new Chat(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return chats;
    }

    public static ArrayList<Message> getMessagesByUserIdOpponentId(int userId, int opponentId) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, message_text, read_by_receiver, sent_time, chat_id, user_id, opponent_user_id " +
                            "FROM messages " +
                            "WHERE user_id=? AND opponent_user_id=? " +
                            "ORDER BY latest_message_time DESC"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, opponentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boolean readByReceiver = resultSet.getInt("read_by_receiver") == 1;
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message_text"),
                        readByReceiver,
                        resultSet.getTimestamp("sent_time"),
                        getChat(resultSet.getInt("chat_id")),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public static ArrayList<Message> getMessagesByChatId(int chatId) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, message_text, read_by_receiver, sent_time, chat_id, user_id, opponent_user_id " +
                            "FROM messages " +
                            "WHERE chat_id=? " +
                            "ORDER BY sent_time ASC"
            );
            preparedStatement.setInt(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boolean readByReceiver = resultSet.getInt("read_by_receiver") == 1;
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message_text"),
                        readByReceiver,
                        resultSet.getTimestamp("sent_time"),
                        getChat(resultSet.getInt("chat_id")),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public static ArrayList<FriendRequest> getFriendRequestByUserID(int userId) {
        ArrayList<FriendRequest> friends = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, sent_time, user_id, request_sender_id " +
                            "FROM friends_requests " +
                            "WHERE user_id=? " +
                            "ORDER BY sent_time DESC"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friends.add(new FriendRequest(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("sent_time"),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("request_sender_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return friends;
    }

    public static ArrayList<FriendRequest> getFriendRequestByRequestSenderID(int userId) {
        ArrayList<FriendRequest> friends = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, sent_time, user_id, request_sender_id " +
                            "FROM friends_requests " +
                            "WHERE request_sender_id=? " +
                            "ORDER BY sent_time DESC"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friends.add(new FriendRequest(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("sent_time"),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("request_sender_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return friends;
    }

    public static boolean isFriendRequestSent(int requestSenderId, int userId) {
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, sent_time, user_id, request_sender_id " +
                            "FROM friends_requests " +
                            "WHERE request_sender_id=? AND user_id=? "
            );
            preparedStatement.setInt(1, requestSenderId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public static boolean areFriends(int userId, int friend_id) {
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, added_time, user_id, friend_id " +
                            "FROM friends " +
                            "WHERE user_id=? AND friend_id=? " +
                            "ORDER BY added_time DESC"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friend_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<Language> getLanguages() {
        ArrayList<Language> languages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, code " +
                            "FROM languages"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                languages.add(new Language(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return languages;
    }

    public static ArrayList<Publication> getPublications() {
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, description, rating " +
                            "FROM publications"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publications.add(new Publication(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publications;
    }

    public static ArrayList<NewsItem> getNewsItems() {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, picture_url, language_id, publication_id " +
                            "FROM news"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsItems.add(new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newsItems;
    }

    public static int countUsers() {
        int amount = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM users"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                amount++;
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return amount;
    }

    public static int countPosts() {
        int amount = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM posts"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                amount++;
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return amount;
    }

    public static User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users WHERE email = ? LIMIT 1"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static User getUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users WHERE id = ? LIMIT 1"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static Chat getChat(int id) {
        Chat chat = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, created_date, latest_message_text, latest_message_time, user_id, opponent_user_id " +
                            "FROM chats WHERE id = ? LIMIT 1"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                chat = new Chat(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return chat;
    }

    public static Chat getChatByUserIdOpponentId(int userId, int opponentId) {
        Chat chat = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, created_date, latest_message_text, latest_message_time, user_id, opponent_user_id " +
                            "FROM chats WHERE (user_id = ? AND opponent_user_id = ?) OR " +
                            "(user_id = ? AND opponent_user_id = ?) LIMIT 1"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, opponentId);
            preparedStatement.setInt(3, opponentId);
            preparedStatement.setInt(4, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                chat = new Chat(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return chat;
    }

    public static Message getMessageByChatIdMessageTextSentTimeUserIdOpponentId(Message message) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, message_text, read_by_receiver, sent_time, chat_id, user_id, opponent_user_id " +
                            "FROM messages " +
                            "WHERE chat_id=? AND message_text=? AND sent_time=? AND user_id=? AND opponent_user_id=? " +
                            "ORDER BY sent_time ASC"
            );
            preparedStatement.setInt(1, message.getChat().getId());
            preparedStatement.setString(2, message.getMessageText());
            preparedStatement.setTimestamp(3, message.getSentTime());
            preparedStatement.setInt(4, message.getUser().getId());
            preparedStatement.setInt(5, message.getOpponent().getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                message = new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message_text"),
                        resultSet.getInt("read_by_receiver") == 1,
                        resultSet.getTimestamp("sent_time"),
                        getChat(resultSet.getInt("chat_id")),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return message;
    }

    public static Message getMessageByChatIdMessageTextSentTime(int chatId, String messageText, Timestamp sentTime) {
        Message message = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, message_text, read_by_receiver, sent_time, chat_id, user_id, opponent_user_id " +
                            "FROM messages " +
                            "WHERE chat_id=? AND message_text=? AND sent_time=? LIMIT 1"
            );
            preparedStatement.setInt(1, chatId);
            preparedStatement.setString(2, messageText);
            preparedStatement.setTimestamp(3, sentTime);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                message = new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message_text"),
                        resultSet.getInt("read_by_receiver") == 1,
                        resultSet.getTimestamp("sent_time"),
                        getChat(resultSet.getInt("chat_id")),
                        getUser(resultSet.getInt("user_id")),
                        getUser(resultSet.getInt("opponent_user_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return message;
    }

    public static Post getPost(int id) {
        Post post = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, author_id " +
                            "FROM posts WHERE id = ? LIMIT 1"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getInt("author_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    public static Language getLanguage (int id) {
        Language language = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, code " +
                            "FROM languages " +
                            "WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                language = new Language(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return language;
    }

    public static Language getLanguageByCode (String code) {
        Language language = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, code " +
                            "FROM languages " +
                            "WHERE code = ?"
            );
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                language = new Language(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return language;
    }

    public static Publication getPublication (int id) {
        Publication publication = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, description, rating " +
                            "FROM publications " +
                            "WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                publication = new Publication(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publication;
    }

    public static NewsItem getNewsItem (int id) {
        NewsItem newsItem = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id " +
                            "FROM news n " +
                            "INNER JOIN languages l ON l.id = n.language_id " +
                            "INNER JOIN publications p ON p.id = n.publication_id " +
                            "WHERE n.id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                newsItem = new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newsItem;
    }

    public static boolean deleteUser(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deletePost(int id, int author_id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM posts WHERE id =? AND author_id=? "
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, author_id);
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deleteFriendRequest(int requestSenderId, int userId) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM friends_requests WHERE request_sender_id =? AND user_id=? "
            );
            preparedStatement.setInt(1, requestSenderId);
            preparedStatement.setInt(2, userId);
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deleteFriend(int friendId, int userId) {
        int rows_deleted = 0;
        int rows_deleted1 = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM friends WHERE friend_id =? AND user_id=? "
            );
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "DELETE FROM friends WHERE user_id =? AND friend_id=? "
            );
            preparedStatement.setInt(1, friendId);
            preparedStatement.setInt(2, userId);
            preparedStatement1.setInt(1, friendId);
            preparedStatement1.setInt(2, userId);
            rows_deleted = preparedStatement.executeUpdate();
            rows_deleted1 = preparedStatement1.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0 && rows_deleted1 > 0;
    }

    public static boolean deleteLanguage(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM languages WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deletePublication(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM publications WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deleteNewsItems(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM news WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean savePost(Post post) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE posts " +
                            "SET title=?, short_content=?, content=? " +
                            "WHERE id =? AND author_id=? "
            );
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getShortContent());
            preparedStatement.setString(3, post.getContent());
//            preparedStatement.setTimestamp(4, post.getPostDate());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.setInt(5, post.getUser().getId());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveChat(Chat chat) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE chats " +
                            "SET latest_message_text=?, latest_message_time=? " +
//                            "WHERE (user_id=? AND opponent_user_id=?) OR " +
//                            "(user_id=? AND opponent_user_id=?)" +
                            "WHERE id=" + chat.getId()
            );
            preparedStatement.setString(1, chat.getLatestMessageText());
            preparedStatement.setTimestamp(2, chat.getLatestMessageTime());
//            preparedStatement.setInt(3, chat.getUser().getId());
//            preparedStatement.setInt(4, chat.getOpponent().getId());
//            preparedStatement.setInt(5, chat.getOpponent().getId());
//            preparedStatement.setInt(6, chat.getUser().getId());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveMessageSetReadByReceiver(int messageId) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE messages " +
                            "SET read_by_receiver=1 " +
                            "WHERE id=" + messageId
            );
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUser(User user) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET email=?, password=?, full_name=?, picture_url=?, birthdate=? " +
                            "WHERE id =" + user.getId()
            );
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPictureURL());
            preparedStatement.setDate(5, user.getSqlBirthdate());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUserInfo(int id, String email, String fullName, java.util.Date birthdate) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET email=?, full_name=?, birthdate=? " +
                            "WHERE id =" + id
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fullName);
            preparedStatement.setDate(3, new java.sql.Date(birthdate.getTime()));
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUserPicture(int id, String pictureURL) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET picture_url=? " +
                            "WHERE id =" + id
            );
            preparedStatement.setString(1, pictureURL);
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUserPassword(int id, String password) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET password=? " +
                            "WHERE id =" + id
            );
            preparedStatement.setString(1, password);
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveLanguage(Language language) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE languages " +
                            "SET name=?, code=? " +
                            "WHERE id =" + language.getId()
            );
            preparedStatement.setString(1, language.getName());
            preparedStatement.setString(2, language.getCode());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean savePublication(Publication publication) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE publications " +
                            "SET name=?, description=?, rating=? " +
                            "WHERE id =" + publication.getId()
            );
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setDouble(3, publication.getRating());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveNewsItem(NewsItem newsItem) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE news " +
                            "SET title=?, short_content=?, content=?, picture_url=?, language_id=?, publication_id=? " +
                            "WHERE id =" + newsItem.getId()
            );
            preparedStatement.setString(1, newsItem.getTitle());
            preparedStatement.setString(2, newsItem.getShortContent());
            preparedStatement.setString(3, newsItem.getContent());
            preparedStatement.setString(4, newsItem.getPictureURL());
//            preparedStatement.setTimestamp(5, newsItem.getPostDate());
            preparedStatement.setInt(5, newsItem.getLanguage().getId());
            preparedStatement.setInt(6, newsItem.getPublication().getId());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }
}
