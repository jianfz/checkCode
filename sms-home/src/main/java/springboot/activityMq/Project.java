package springboot.activityMq;

//@Component
public class Project {
//
//	@Autowired
//	private JmsMessagingTemplate jmsMessagingTemplate;
//	@Autowired
//	private Queue queue;
//	public void sendMessage(Destination destination,final String message){
//		jmsMessagingTemplate.convertAndSend(destination,message);
//	}
//	@Scheduled(fixedDelay=3000)
//	public void send(){
//		this.jmsMessagingTemplate.convertAndSend(this.queue,"hi,textmq");
//	}
	public static void main(String[] args) {
		Double random = Math.random() * 1000;
		System.out.println(random.intValue());
	}
}
