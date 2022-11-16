//package com.example.chattingback.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONArray;
//import com.example.backend.utils.MyBeanUtils;
//import com.example.chattingback.Resource.mapper.FriendMessageMapper;
//import com.example.chattingback.eneity.dbEntities.FriendMessage;
//import com.example.chattingback.eneity.dbEntities.GroupMessage;
//import com.example.chattingback.enums.RedisCache;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.*;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@Slf4j
//@Component
//public class RedisUtil {
//
//    private static RedisUtil redisUtil;
//    @PostConstruct
//    private void init(){
//        redisUtil = this;
//        redisUtil.friendMessageMapper = this.friendMessageMapper;
//        redisUtil.groupMessageMapper = this.groupMessageMapper;
//    }
//
//    @Resource
//    private  com.example.chattingback.Resource.mapper.GroupMessage groupMessageMapper;
//
//
//    @Resource
//    private FriendMessageMapper friendMessageMapper;
//
//    static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
//    public static void main(String[] args) {
//        String s = get("cache:group:messages:Echim9的大家庭");
//        System.out.println(s);
//    }
//
//
//    public static String isValidCodeSHA;
//
//    private static JedisPool jedisPool;
//
//
//
//    @Autowired
//    public void init(JedisPool jedisPool) {
//        RedisUtil.jedisPool = jedisPool;
//        loadScript();
//    }
//
//    public static String isValidCodcoeSHA;
//    public static final String isValidCode =
//            "local code=redis.call('get',KEYS[1]) " +
//                    "if code==false then " +
//                    "return 0 " +
//                    "end " +
//                    "if code~=ARGV[1] then " +
//                    "return 0 " +
//                    "end " +
//                    "redis.call('del',KEYS[1]) " +
//                    "return 1 ";
//
//    private static void loadScript() {
//        try (Jedis jedis = jedisPool.getResource()) {
//            isValidCodeSHA = jedis.scriptLoad(isValidCode);
//        } catch (Exception e) {
//            log.error("ERROR:", e);
//        }
//    }
//
//    public static Object eval(String script, List<String> KEYS, List<String> ARGV) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.eval(script, KEYS, ARGV);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Object evalSHA(String scriptSHA, List<String> KEYS, List<String> ARGV) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.evalsha(scriptSHA, KEYS, ARGV);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    //插入
//    public static String set(String key, String value) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.set(key, value);
//        } catch (Exception e) {
//            log.error("ERROR:", e);
//            return null;
//        }
//    }
//
//    //插入带过期时间
//    public static String set(String key, String val, long ex) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.setex(key, ex, val);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    //获取
//    public static String get(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.get(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    //获取过期时间
//    public static Long ttl(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.ttl(key);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    //
//    public static Long zadd(String key, Double score, String f) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zadd(key, score, f);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Long zadd(String key, Map<String, Double> fv) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zadd(key, fv);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Set<Tuple> zrevrangeWithScores(String key, Long start, Long stop) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zrevrangeWithScores(key, start, stop);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Double zscore(String key, String member) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zscore(key, member);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Long zrank(String key, String member) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zrank(key, member);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Long zrem(String key, String... member) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zrem(key, member);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Boolean exists(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.exists(key);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static Long zcard(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.zcard(key);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static ScanResult<String> scan(String cursor, ScanParams scanParams) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.scan(cursor, scanParams);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Long del(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.del(key);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static <T> T isExitInCache(String id, T obj) {
//        boolean isExit = StringUtils.isNotEmpty(get(id));
//        JSONObject jsonObject = JSONObject.parseObject(get(id));
//        if (isExit) {
//            return (T) JSONObject.toJavaObject(jsonObject, obj.getClass());
//        }
//        return null;
//    }
//
//    public static Boolean delete(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            jedis.del(key + "*");
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static Set<String> getkeys(String key) {
//        try (Jedis jedis = jedisPool.getResource()) {
//            return jedis.keys(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    public static boolean redisCacheTOMysql(ArrayList messages) {
//        //首先判断message类型
//        //根据message类型进行mapper调用直接储存进mysql
//        //清除redis里的信息缓存
//        //返回一个布尔值
//        if (ObjectUtils.isNotEmpty(messages)) {
//            Class<?> aClass = messages.get(0).getClass();
//            if (aClass.equals(FriendMessage.class)) {
//                messages.forEach(message -> {
//                    redisUtil.friendMessageMapper.insert(MyBeanUtils.copyProperties(message, FriendMessage.class));
//                });
//                RedisUtil.delete(RedisCache.CACHE_USER_FRIENDS_MESSAGES);
//                return true;
//            }
//            if (aClass.equals(GroupMessage.class)) {
//                messages.forEach(message -> {
//                    redisUtil.groupMessageMapper.insert(MyBeanUtils.copyProperties(message, GroupMessage.class));
//                });
//                RedisUtil.delete(RedisCache.CACHE_GROUPS_MESSAGES);
//                return true;
//            }
//        } else {
//            return false;
//        }
//        return false;
//    }
//
//    /*
//    创建信息存入redis简便方法
//    * */
//    public static  <T> boolean setMessageCacheToRedis(T message){
//        //判断message属于哪一个类
//        //获取message的发送者id
//        //将message存入对应的redis储存库
//        //返回是否存入成功
//        Class<?> aClass = message.getClass();
//        if (aClass.equals(FriendMessage.class)) {
//            FriendMessage friendMessage = MyBeanUtils.copyProperties(message, FriendMessage.class);
//            String userId = friendMessage.getUserId();
//            String friendId = friendMessage.getFriendId();
//            String roomId;
//            //获取房间号
//            if (friendId.compareTo(userId) > 0) {
//                roomId = friendId + userId;
//            } else {
//                roomId = userId + friendId;
//            }
//            String oldJsonArray = RedisUtil.get(RedisCache.CACHE_USER_FRIENDS_MESSAGES + roomId);
//            //theOldGroupMessages表示当前群组在redis里全部消息列表
//            ArrayList<FriendMessage> theOldFriendMessages = new ArrayList<>();
//            //如果只有一个的话则用object解析
//            if (oldJsonArray.startsWith("{")){
//                //只有一条信息，那就加入arraylist之后再次进行存储
//                com.alibaba.fastjson2.JSONObject oldJsonObject = com.alibaba.fastjson2.JSONObject.parseObject(oldJsonArray);
//                FriendMessage oldFirstFriendMessage = MyBeanUtils.copyProperties(oldJsonObject, FriendMessage.class);
//                theOldFriendMessages.add(oldFirstFriendMessage);
//            } else if (oldJsonArray.isEmpty()) {
//                //没有信息 直接存进去
//                theOldFriendMessages.add(friendMessage);
//            }else {
//                //如果本来就有多条信息，那就转json转实体加信息再转json
//                JSONArray jsonArray = JSONArray.parseArray(oldJsonArray);
//                List<FriendMessage> friendMessages = jsonArray.toJavaList(FriendMessage.class);
//                theOldFriendMessages.addAll(friendMessages);
//                theOldFriendMessages.add(friendMessage);
//            }
//            String newJsonFriendMessagesArray = JSON.toJSONString(theOldFriendMessages);
//            try {
//                RedisUtil.set(RedisCache.CACHE_USER_FRIENDS_MESSAGES + roomId, newJsonFriendMessagesArray);
//                return true;
//            }catch (Exception e) {
//                System.out.println("1");
//                logger.error(e.getMessage());
//                return false;
//            }
//        }else if (aClass.equals(GroupMessage.class)){
//            GroupMessage groupMessage = MyBeanUtils.copyProperties(message, GroupMessage.class);
//            String groupId = groupMessage.getGroupId();
//            String oldJsonArray = RedisUtil.get(RedisCache.CACHE_GROUPS_MESSAGES + groupId);
//            //theOldGroupMessages表示当前群组在redis里全部消息列表
//            ArrayList<GroupMessage> theOldGroupMessages = new ArrayList<>();
//            //如果只有一个的话则用object解析
//            if (oldJsonArray.startsWith("{")){
//                //只有一条信息，那就加入arraylist之后再次进行存储
//                com.alibaba.fastjson2.JSONObject oldJsonObject = com.alibaba.fastjson2.JSONObject.parseObject(oldJsonArray);
//                GroupMessage oldFirstGroupMessage = MyBeanUtils.copyProperties(oldJsonObject, GroupMessage.class);
//                theOldGroupMessages.add(oldFirstGroupMessage);
//            } else if (oldJsonArray.isEmpty()) {
//                //没有信息 直接存进去
//                theOldGroupMessages.add(groupMessage);
//            }else {
//                //如果本来就有多条信息，那就转json转实体加信息再转json
//                JSONArray jsonArray = JSONArray.parseArray(oldJsonArray);
//                List<GroupMessage> groupMessages = jsonArray.toJavaList(GroupMessage.class);
//                theOldGroupMessages.addAll(groupMessages);
//                theOldGroupMessages.add(groupMessage);
//            }
//            String newJsonGroupMessagesArray = JSON.toJSONString(theOldGroupMessages);
//            try {
//                String set = RedisUtil.set(RedisCache.CACHE_GROUPS_MESSAGES + groupId, newJsonGroupMessagesArray);
//                logger.info(set);
//                return true;
//            }catch (Exception e) {
//                System.out.println("2");
//                logger.error(e.getMessage());
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public static ArrayList<GroupMessage> getGroupMessagesFromRedis(String groupId){
//        //从redis里获取聊天信息
//        //将聊天信息转为messageArraylist实体类
//        try {
//            String messagesArr = RedisUtil.get(RedisCache.CACHE_GROUPS_MESSAGES + groupId);
//            if (messagesArr.startsWith("{")) {
//                com.alibaba.fastjson2.JSONObject jsonObject = com.alibaba.fastjson2.JSONObject.parseObject(messagesArr);
//                GroupMessage groupMessage = jsonObject.toJavaObject(GroupMessage.class);
//                ArrayList groupMessages = new ArrayList<GroupMessage>();
//                groupMessages.add(groupMessage);
//                return groupMessages;
//            }
//            else if (StringUtils.isEmpty(messagesArr)){
//                return null;
//            }
//            else {
//                JSONArray MessagesArrJson = JSONArray.parseArray(messagesArr);
//                List<GroupMessage> groupMessages = MessagesArrJson.toJavaList(GroupMessage.class);
//                return (ArrayList) groupMessages;
//            }
//        }catch (Exception e) {
//            System.out.println("3");
//            logger.error(e.getMessage());
//            return null;
//        }
//    }
//
//    public static ArrayList<FriendMessage> getFriendMessagesFromRedis(String roomId){
//        //从redis里获取聊天信息
//        //将聊天信息转为messageArraylist实体类
//        try {
//            String messagesArr = RedisUtil.get(RedisCache.CACHE_USER_FRIENDS_MESSAGES + roomId);
//            if (messagesArr.startsWith("{")) {
//                com.alibaba.fastjson2.JSONObject jsonObject = com.alibaba.fastjson2.JSONObject.parseObject(messagesArr);
//                FriendMessage friendMessage = jsonObject.toJavaObject(FriendMessage.class);
//                ArrayList friendMessages = new ArrayList<FriendMessage>();
//                friendMessages.add(friendMessage);
//                return friendMessages;
//            }
//            else if (StringUtils.isEmpty(messagesArr)){
//                return null;
//            }
//            else {
//                JSONArray MessagesArrJson = JSONArray.parseArray(messagesArr);
//                List<FriendMessage> friendMessages = MessagesArrJson.toJavaList(FriendMessage.class);
//                return (ArrayList) friendMessages;
//            }
//        }catch (Exception e) {
//            System.out.println("4");
//            logger.error(e.getMessage());
//            return null;
//        }
//    }
//
//}