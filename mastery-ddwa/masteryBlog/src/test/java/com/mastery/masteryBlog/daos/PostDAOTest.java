/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.daos;

import com.mastery.masteryBlog.dtos.Category;
import com.mastery.masteryBlog.dtos.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author corey
 */
public class PostDAOTest {
    
    public PostDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPost method, of class PostDAO.
     */
    @Test
    public void testAddPost() {
        System.out.println("addPost");
        Post post = null;
        PostDAO instance = new PostDAOImpl();
        Post expResult = null;
        Post result = instance.addPost(post);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostById method, of class PostDAO.
     */
    @Test
    public void testGetPostById() {
        System.out.println("getPostById");
        int postId = 0;
        PostDAO instance = new PostDAOImpl();
        Post expResult = null;
        Post result = instance.getPostById(postId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPosts method, of class PostDAO.
     */
    @Test
    public void testGetAllPosts() {
        System.out.println("getAllPosts");
        PostDAO instance = new PostDAOImpl();
        List<Post> expResult = null;
        List<Post> result = instance.getAllPosts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePost method, of class PostDAO.
     */
    @Test
    public void testUpdatePost() {
        System.out.println("updatePost");
        Post post = null;
        PostDAO instance = new PostDAOImpl();
        instance.updatePost(post);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePost method, of class PostDAO.
     */
    @Test
    public void testDeletePost() {
        System.out.println("deletePost");
        int postId = 0;
        PostDAO instance = new PostDAOImpl();
        instance.deletePost(postId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostsByCatagoryId method, of class PostDAO.
     */
    @Test
    public void testGetPostsByCatagoryId() {
        System.out.println("getPostsByCatagoryId");
        int catagoryId = 0;
        PostDAO instance = new PostDAOImpl();
        List<Post> expResult = null;
        List<Post> result = instance.getPostsByCatagoryId(catagoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostsByTagName method, of class PostDAO.
     */
    @Test
    public void testGetPostsByTagName() {
        System.out.println("getPostsByTagName");
        String tagName = "";
        PostDAO instance = new PostDAOImpl();
        List<Post> expResult = null;
        List<Post> result = instance.getPostsByTagName(tagName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostsByUserName method, of class PostDAO.
     */
    @Test
    public void testGetPostsByUserName() {
        System.out.println("getPostsByUserName");
        String username = "";
        PostDAO instance = new PostDAOImpl();
        List<Post> expResult = null;
        List<Post> result = instance.getPostsByUserName(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostsByDisplayId method, of class PostDAO.
     */
    @Test
    public void testGetPostsByDisplayId() {
        System.out.println("getPostsByDisplayId");
        int displayId = 0;
        PostDAO instance = new PostDAOImpl();
        List<Post> expResult = null;
        List<Post> result = instance.getPostsByDisplayId(displayId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePending method, of class PostDAO.
     */
    @Test
    public void testUpdatePending() {
        System.out.println("updatePending");
        Post post = null;
        PostDAO instance = new PostDAOImpl();
        instance.updatePending(post);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addImage method, of class PostDAO.
     */
    @Test
    public void testAddImage() {
        System.out.println("addImage");
        String imagePath = "";
        int id = 0;
        PostDAO instance = new PostDAOImpl();
        instance.addImage(imagePath, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Transactional
    public class PostDAOImpl implements PostDAO {

    @Autowired
    JdbcTemplate jdbc;
    
    private final String createPost = "Insert into Post(`username`, title, postDate, catagoryId, posting, isApproved, imagePath) Values (?,?,?,?,?,?,?)";
    private final String getAllPosts = "Select Post.postId, Post.`username`, Post.title, Post.postDate, cat.catagoryId, cat.`description`, Post.posting, group_concat(t.tagId), group_concat(t.tagName), Post.isApproved, Post.imagePath From Catagory As cat"
                                     + " Inner Join Post On Post.catagoryId = cat.catagoryId"
                                     + " Left Join PostTags pt on Post.postId = pt.postId"
                                     + " Left Join Tags t on pt.tagId = t.tagId"
                                     + " group by post.postId";
    private final String getPostById = "Select Post.postId, Post.`username`, Post.title, Post.postDate, cat.catagoryId, cat.`description`, Post.posting, group_concat(t.tagId), group_concat(t.tagName), Post.isApproved, Post.imagePath From Catagory As cat"
                                     + " Inner Join Post On Post.catagoryId = cat.catagoryId"
                                     + " Left Join PostTags pt on Post.postId = pt.postId"
                                     + " Left Join Tags t on pt.tagId = t.tagId"
                                     + " Where Post.postId = ?"
                                     + " group by post.postId";
    private final String getPostByCategoryId = "Select Post.postId, Post.`username`, Post.title, Post.postDate, cat.catagoryId, cat.`description`, Post.posting, group_concat(t.tagId), group_concat(t.tagName), Post.isApproved, Post.imagePath From Catagory As cat"
            + " Inner Join Post On Post.catagoryId = cat.catagoryId"
            + " Left Join PostTags pt on Post.postId = pt.postId"
            + " Left Join Tags t on pt.tagId = t.tagId"
            + " Where cat.catagoryId = ?"
            + " group by post.postId";
    private final String getPostByTagName = "Select Post.postId, Post.`username`, Post.title, Post.postDate, cat.catagoryId, cat.`description`, Post.posting, group_concat(t.tagName), Post.isApproved, Post.imagePath From Catagory As cat "
            + "Join Post On Post.catagoryId = cat.catagoryId" 
            + " left join PostTags pt on Post.postId = pt.postId" 
            + " left join Tags t on pt.tagId = t.tagId" 
            + " where t.tagName = ?" 
            + " group by post.postId";
    private final String updatePost = "Update Post Set `username` = ?, title = ?, catagoryId = ?, posting = ?, imagePath = ? Where postId = ?";
    private final String updatePending = "Update Post Set `username` = ?, title = ?, postDate = ?, catagoryId = ?, posting = ?, isApproved = ?, imagePath = ? Where postId = ?";
    private final String deletePost = "Delete From Post Where postId = ?";
    private final String addImagePath = "Update Post Set imagePath = ? Where postId = ?";
    private final String getStaticPosts = "Select Post.postId, Post.`username`, Post.title, Post.postDate, cat.catagoryId, cat.`description`, Post.posting, group_concat(t.tagId), group_concat(t.tagName), Post.isApproved, Post.isStatic, Post.imagePath From Catagory As cat"
            + " Inner Join Post On Post.catagoryId = cat.catagoryId"
            + " Left Join PostTags pt on Post.postId = pt.postId"
            + " Left Join Tags t on pt.tagId = t.tagId"
            + " Where post.isStatic = true"
            + " group by post.postId";
    

    @Override
    public Post addPost(Post post) {
        jdbc.update(createPost, post.getUsername(), post.getTitle(), post.getDateTime(), post.getCategory().getCatagoryId(), post.getPosting(), post.isApproved(), post.getImagePath());
        post.setPostId(jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return post;   
        
    }

    @Override
    public Post getPostById(int postId) {
        return jdbc.queryForObject(getPostById, new PostJDBCMapper(), postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return jdbc.query(getAllPosts, new PostJDBCMapper());
    }

    @Override
    public void updatePost(Post post) {
        jdbc.update(updatePost, post.getUsername(), post.getTitle(), post.getCategory().getCatagoryId(), post.getPosting(), post.getPostId(), post.getImagePath());
    }

    @Override
    public void deletePost(int postId) {
        jdbc.update(deletePost, postId);
    }
    

    @Override
    public List<Post> getPostsByCatagoryId(int categoryId) {
        return jdbc.query(getPostByCategoryId, new PostJDBCMapper(), categoryId);
    }

    @Override
    public List<Post> getPostsByTagName(String tagName) {
        return jdbc.query(getPostByTagName, new PostJDBCMapper(), tagName);
    }

    @Override
    public List<Post> getPostsByUserName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByDisplayId(int displayId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePending(Post post) {
        jdbc.update(updatePending, post.getUsername(), post.getTitle(), post.getDateTime(), post.getCategory().getCatagoryId(), post.getPosting(), post.isApproved(), post.getImagePath(), post.getPostId());
    }

    @Override
    public void addImage(String imagePath, int id) {
        jdbc.update(addImagePath, imagePath, id);
    }

        @Override
        public List<Post> getAllStaticPost() {
            return jdbc.query(getStaticPosts, new PostJDBCMapper());
        }
    

    
        public final class PostJDBCMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            
            
            
            Category category = new Category();
            
            category.setCatagoryId(rs.getInt("catagoryId"));
            category.setDescription(rs.getString("description"));
            
            Post post = new Post();
            
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            post.setPostId(rs.getInt("postId"));
            post.setUsername(rs.getString("username"));
            post.setTitle(rs.getString("title"));
            post.setDateTime(LocalDateTime.parse(rs.getString("postDate"), format));
            post.setCategory(category);
            post.setPosting(rs.getString("posting"));
            post.setTagList(rs.getString("group_concat(t.tagName)"));
            post.setApproved(rs.getBoolean("isApproved"));
            post.setImagePath(rs.getString("imagePath"));
            
            return post;
            
        }
    }
   
 
    
    }
    
}
