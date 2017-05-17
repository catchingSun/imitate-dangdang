package com.tarena.dang.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.tarena.dang.dao.AddressDao;
import com.tarena.dang.dao.BookDao;
import com.tarena.dang.dao.CategoryDao;
import com.tarena.dang.dao.ItemDao;
import com.tarena.dang.dao.OrderDao;
import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.dao.UserDao;
import com.tarena.dang.entity.Address;
import com.tarena.dang.entity.CartItem;
import com.tarena.dang.entity.Category;
import com.tarena.dang.entity.Item;
import com.tarena.dang.entity.Order;
import com.tarena.dang.entity.Product;
import com.tarena.dang.entity.User;
import com.tarena.dang.impl.CartServiceImpl;
import com.tarena.dang.impl.JDBCAddressDao;
import com.tarena.dang.impl.JDBCBookDao;
import com.tarena.dang.impl.JDBCCategoryDao;
import com.tarena.dang.impl.JDBCItemDao;
import com.tarena.dang.impl.JDBCOrderDao;
import com.tarena.dang.impl.JDBCProductDao;
import com.tarena.dang.impl.JDBCUserDao;
import com.tarena.dang.impl.MainServiceImpl;
import com.tarena.dang.impl.UserService;
import com.tarena.dang.impl.UserServiceImpl;
import com.tarena.dang.service.CartService;
import com.tarena.dang.service.MainService;
import com.tarena.dang.util.CookieUtil;
import com.tarena.dang.util.EmailUtil;
import com.tarena.dang.util.EncryptUtil;

public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String url = request.getRequestURI();
		url = url.substring(url.lastIndexOf('/'), url.lastIndexOf('.'));
		System.out.println("ActionServlet" + url);
		if (url.equals("/toLogin")) {
			// response.sendRedirect("index.jsp");

			request.getRequestDispatcher("/user/login_form.jsp").forward(
					request, response);

		} else if (url.endsWith("/login")) {
			String email = request.getParameter("email");
			String pwd = request.getParameter("password");
			pwd = EncryptUtil.md5Encrypt(pwd);
			UserDao dao = new JDBCUserDao();
			User user = dao.findByLogin(email, pwd);
			HttpSession session = request.getSession();
			if (user == null) {
				request.setAttribute("errorMsg", "邮箱或密码错误");
				request.getRequestDispatcher("/user/login_form.jsp").forward(
						request, response);
			} else if(user.getEmailVerify()){
				session.setAttribute("user", user);
				String ip = request.getRemoteAddr();
				user.setLastLoginTime(System.currentTimeMillis());
				user.setLastLoginIp(ip);
				dao.doUpdateIpTime(user.getId(), user.getLastLoginTime(), user
						.getLastLoginIp());
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			} else {
				
				String code = user.getEmailVerifyCode() + "-" + user.getId();
				
				session.setAttribute("emailVerifyCode", code);
				session.setAttribute("email", email);
				
				
				
				request.getRequestDispatcher("/user/verify_form.jsp").forward(
						request, response);
			}
		}else if(url.equals("/validEmailVerifyCode")){
			//System.out.println("==============");
			
			UserService us = new UserServiceImpl();
			try {
				User user = us.checkEmialVarifyCode(request.getParameter("code"));
				if(user.getEmailVerify()){
					request.getRequestDispatcher("/user/register_ok.jsp").forward(
							request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (url.equals("/toRegist")) {
			request.getRequestDispatcher("user/regist_form.jsp").forward(
					request, response);
		} else if (url.equals("/regist")) {
			HttpSession session = request.getSession();
			User user = new User();
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			user.setEmail(email);
			user.setNickname(nickname);
			user.setPassword(password);
			user.setEmailVerifyCode(UUID.randomUUID().toString());
			user.setLastLoginIp(request.getRemoteAddr());
			UserService userService = new UserServiceImpl();
			try {
				userService.regist(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String emailVerifyCode = user.getEmailVerifyCode() + "-"
					+ user.getId();
			//session.setAttribute("emailVerifyCode", emailVerifyCode);
			session.setAttribute("email", email);
			session.setAttribute("user", user);
			
			EmailUtil.sendEmail(email, emailVerifyCode);
			
			request.getRequestDispatcher("/user/verify_form.jsp").forward(request,
					response);

		}
		else if (url.equals("/valid")) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			String scode = (String) session.getAttribute("imageCode");
			String code = request.getParameter("code");
			if (code.equals(scode.toLowerCase())) {
				out.print(true);
			} else {
				out.print(false);
			}
		} else if (url.equals("/validEmail")) {
			UserDao dao = new JDBCUserDao();
			String email = request.getParameter("email");
			User user;
			try {
				user = dao.findByEmail(email);
				if (user == null) {
					out.print(true);
				} else {
					out.print(false);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if (url.equals("/new")) {
			ProductDao proDao = new JDBCProductDao();
			try {
				List<Product> pros = proDao.findNewProduct(8);
				request.setAttribute("pros", pros);
				request.getRequestDispatcher("/main/new.jsp").forward(request,
						response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (url.equals("/newhot")) {
			ProductDao proDao = new JDBCProductDao();

			try {
				List<Product> pros = proDao.findNewHotProduct(10, 1);
				request.setAttribute("pros", pros);
				request.getRequestDispatcher("/main/newhot.jsp").forward(
						request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (url.equals("/booklist")) {
			bookList(request, response);
		}
		else if (url.equals("/hot")) {
			ProductDao proDao = new JDBCProductDao();
			try {
				List<Product> pros = proDao.findHotProduct(4);
				if (pros == null) {
					System.out.println("hotlist is null");
				}
				request.setAttribute("pros", pros);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/main/hot.jsp").forward(request,
					response);
		} 
		else if (url.equals("/recommend")) {
			BookDao bookDao = new JDBCBookDao();
			try {
				List<Product> list = bookDao.findAll();
				Random r = new Random();
				List<Product> pros = new ArrayList<Product>();
				int n = 2, i = 0;
				while (true) {
					int index = r.nextInt(list.size());
					if (pros.contains(list.get(index))) {
						continue;
					}
					pros.add(list.get(index));
					i++;
					if (i == n) {
						break;
					}
				}
				request.setAttribute("pros", pros);
				request.getRequestDispatcher("/main/recommend.jsp").forward(
						request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (url.equals("/category")) {
			MainService service = new MainServiceImpl();
			List<Category> cats = service.findLeftCategory();
			request.setAttribute("cats", cats);
			request.getRequestDispatcher("/main/category.jsp").forward(request,
					response);
		} 
		else if (url.equals("/product")) {// ---------------

			BookDao bookDao = new JDBCBookDao();
			String param = request.getParameter("id");
			int id = Integer.parseInt(param);
			Product pro;
			try {
				pro = bookDao.findById(id);

				request.setAttribute("pro", pro);
				request.getRequestDispatcher("/main/product.jsp").forward(
						request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(id);

		}  else if (url.equals("/address")) {
			List<Address> adds = new ArrayList<Address>();
			AddressDao addressDao = new JDBCAddressDao();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			int id = user.getId();
			try {

				adds = addressDao.findAddressByUserId(id);
				request.setAttribute("adds", adds);
				request.getRequestDispatcher("order/address_form.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (url.equals("/showaddress")) {
			AddressDao addressDao = new JDBCAddressDao();
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			Address address;
			try {
				address = addressDao.findById(id);
				JSONObject json = new JSONObject();
				Object object = json.fromObject(address);
				out.println(object.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}  else if (url.equals("/buy")) {
			HttpSession session = request.getSession();
			CartService cart = (CartService) session.getAttribute("cart");
			if (cart == null) {
				cart = new CartServiceImpl();
				// 有可能用户之前购买过一些商品,尝试恢复之前购买过的商品
				try {
					cart.load(CookieUtil.findCookie("buy_cart", request), cart
							.getBuyPros());
					cart.load(CookieUtil.findCookie("del_cart", request), cart
							.getDelPros());
					session.setAttribute("cart", cart);
					String pidStr = request.getParameter("pid");
					int pid = Integer.parseInt(pidStr);
					Boolean ok = cart.add(pid);
					if (ok) {
						CookieUtil.addCookie("buy_cart", cart.store(cart
								.getBuyPros()), response);
						CookieUtil.addCookie("del_cart", cart.store(cart
								.getDelPros()), response);
					}
					Map<String, Object> result = new HashMap<String, Object>();
					result.put("ok", ok);
					JSONObject jsonObj = JSONObject.fromObject(result);
					out.print(jsonObj.toString());
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} else if (url.equals("/cartlist")) {
			cartList(request, response);
		} else if (url.equals("/update")) {
			HttpSession session = request.getSession();
			CartService cart = (CartService) session.getAttribute("cart");
			String idStr = request.getParameter("id");
			String qtyStr = request.getParameter("qty");
			int id = Integer.parseInt(idStr);
			int qty = Integer.parseInt(qtyStr);
			try {
				cart.update(id, qty);
				CookieUtil.addCookie("buy_cart", cart.store(cart.getBuyPros()),
						response);
				CookieUtil.addCookie("del_cart", cart.store(cart.getDelPros()),
						response);
				request.getRequestDispatcher("/cartlist.do").forward(request,
						response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (url.equals("/delete")) {
			HttpSession session = request.getSession();
			CartService cart = (CartService) session.getAttribute("cart");
			String pidStr = request.getParameter("pid");
			int pid = Integer.parseInt(pidStr);
			try {
				cart.delete(pid);
				CookieUtil.addCookie("buy_cart", cart.store(cart.getBuyPros()),
						response);
				CookieUtil.addCookie("del_cart", cart.store(cart.getDelPros()),
						response);
				request.getRequestDispatcher("/cartlist.do").forward(request,
						response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (url.equals("/recovery")) {
			HttpSession session = request.getSession();
			CartService cart = (CartService) session.getAttribute("cart");
			String pidStr = request.getParameter("pid");
			int pid = Integer.parseInt(pidStr);
			try {
				cart.recovery(pid);
				CookieUtil.addCookie("buy_cart", cart.store(cart.getBuyPros()),
						response);
				CookieUtil.addCookie("del_cart", cart.store(cart.getDelPros()),
						response);
				request.getRequestDispatcher("/cartlist.do").forward(request,
						response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (url.equals("/orderConfirm")) {
			HttpSession session = request.getSession();
			CartService cart = (CartService) session.getAttribute("cart");
			List<CartItem> buyPros;
			try {
				buyPros = cart.getBuyPros();
				double totalCost = cart.cost();
				session.setAttribute("buyPros", buyPros);
				session.setAttribute("totalCost", totalCost);
				request.getRequestDispatcher("order/order_info.jsp").forward(
						request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(url.equals("/saveAddressAndOrder")){
			saveAddressAndOrder(request,response);
			
		}
		else if (url.equals("/exit")) {
			HttpSession session = request.getSession();
			// 移除服务器上session对象中的用户对象
			session.removeAttribute("user");
			// 切换到主页(刷新主页)
			request.getRequestDispatcher("/main/main.jsp").forward(request,
					response);
		}
	}

	public void bookList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CategoryDao catDao = new JDBCCategoryDao();
			String parentId = request.getParameter("pid");
			int pid = Integer.parseInt(parentId);
			List<Category> cats = catDao.findByParentId(pid);
			int totalNum = 0;
			for (Category c : cats) {
				totalNum += c.getPnum();
			}
			String parentName = catDao.getCategoryNameById(pid);
			BookDao bookDao = new JDBCBookDao();
			Integer order = null;
			List<Product> pros;
			String currentId = request.getParameter("cid");
			int cid = Integer.parseInt(currentId);
			Integer page = 1;
			Integer size = 5;
			String pageStr = request.getParameter("page");
			if (pageStr != null && !pageStr.equals("")) {
				page = Integer.parseInt(pageStr);
			}
			String orderStr = request.getParameter("order");
			if (order == null) {
				pros = bookDao.findByCatId(cid, page, size);
			} else {
				order = Integer.parseInt(orderStr);
				System.err.println(order);
				pros = bookDao.findByCatId(cid, page, size);
			}

			int maxPage = bookDao.getTotalPage(cid, size);
			HttpSession session = request.getSession();
			session.setAttribute("pid", pid);
			session.setAttribute("cid", cid);
			session.setAttribute("order", order);
			session.setAttribute("page", page);
			session.setAttribute("cats", cats);
			session.setAttribute("pros", pros);
			session.setAttribute("totalNum", totalNum);
			session.setAttribute("maxPage", maxPage);
			session.setAttribute("parentName", parentName);
			request.getRequestDispatcher("/main/book_list.jsp").forward(
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cartList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			CartService cart = (CartService) session.getAttribute("cart");
			if (cart == null) {
				// 有可能用户之前购买过一些商品,尝试恢复之前购买过的商品
				cart = new CartServiceImpl();
				cart.load(CookieUtil.findCookie("buy_cart", request), cart
						.getBuyPros());
				cart.load(CookieUtil.findCookie("del_cart", request), cart
						.getDelPros());
				session.setAttribute("cart", cart);
			}
			List<CartItem> buyPros = cart.getBuyPros();
			List<CartItem> delPros = cart.getDelPros();
			double totalCost = cart.cost();
			double saving = 0;
			for (int i = 0; i < buyPros.size(); i++) {
				CartItem item = buyPros.get(i);
				saving += (item.getPro().getFixedPrice() - item.getPro()
						.getDangPrice())
						* item.getQty();
			}
			session.setAttribute("buyPros", buyPros);
			session.setAttribute("buyProsSize", buyPros.size());
			session.setAttribute("delPros", delPros);
			session.setAttribute("saving", saving);
			session.setAttribute("totalCost", totalCost);
			request.getRequestDispatcher("/cart/cart_list.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveAddressAndOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//1.获取提交的地址数据放入Address对象中，并将对象中的数据用save方法存入数据库
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			Address address=new Address();//存入d_receive_address表
			String receiveName=request.getParameter("receiveName");
			String fullAddress=request.getParameter("fullAddress");
			String postalCode=request.getParameter("postalCode");
			String phone=request.getParameter("phone");
			String mobile=request.getParameter("mobile");
			address.setReceiveName(receiveName);
			address.setFullAddress(fullAddress);
			address.setPostalCode(postalCode);
			address.setPhone(phone);address.setMobile(mobile);
			address.setUserId(user.getId());
			if(address.getId()==null){
				AddressDao addressDAO=new JDBCAddressDao();
				addressDAO.save(address);
			}
			
			//2.将订单信息写入d_order表
			CartService cart=(CartService) session.getAttribute("cart");
			Order order=new Order();
			order.setAddress(address);
			order.setOrderDesc("很好");
			order.setOrderTime(System.currentTimeMillis());
			order.setStatus(1);//1=Constant.WAIT_PAY (等待付款)
			order.setTotalPrice(cart.cost());
			OrderDao orderDAO=new JDBCOrderDao();
			orderDAO.save(order);
			
			
			//Integer orderId=order.getId();
			//Double totalCost=order.getTotalPrice();
			//3.向d_item写入若干条记录(购买几种商品,就写几条记录)
			ItemDao itemDAO=new JDBCItemDao();
			List<CartItem> buyPros=cart.getBuyPros();
			for(CartItem c:buyPros){
				Product pro=c.getPro();
				Item item=new Item();
				item.setOrderId(order.getId());
				item.setProductId(pro.getId());
				item.setProductName(pro.getProductName());
				item.setProductNum(c.getQty());
				item.setDangPrice(pro.getDangPrice());
				item.setAmount(c.getQty()*pro.getDangPrice());
				itemDAO.save(item);
			}
			
			
			//4.订单创建成功后,将Session和Cookie中的商品信息删除并跳到order_ok.jsp
			session.removeAttribute("cart");
			CookieUtil.delete("buy_cart",response);
			CookieUtil.delete("del_cart",response);
			request.getRequestDispatcher("/order/order_ok.jsp")
			.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
