package io.javabrains.moviecatlogservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourse {
	
	
	private static final Logger log = LoggerFactory.getLogger(MovieCatalogResourse.class);

	
	@Autowired
	private RestTemplate restTemplate;
	
	// to check port number in ribbon client 
	@Value("${server.port}")
	private String port;
	
//	@Autowired
//	private DiscoveryClient  discoveryClient;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;;

	
	// movie-info = 82
	// rating = 83
	@RequestMapping("/{userId}")
	public List<CatalogItem> getcatalog(@PathVariable("userId") String userId){
		log.info("catalog -- sumit");


		// get all rated movie id  //employee-zuul-service
		//List<Rating> ratings = Arrays.asList(new Rating("1234", 4),new Rating("5678", 3) );
		UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/1", UserRating.class);   //localhost:8083
//		UserRating userRating = restTemplate.getForObject("http://producerrate/ratingsdata/users/1", UserRating.class);   //localhost:8083
		// for each movie id ,call movie info service and get details
		
		List<CatalogItem> catalogItems= new ArrayList<>();
		
		for(Rating r : userRating.getUserRating()){
			Movie movie=	restTemplate.getForObject("http://movie-info-service/movies/"+r.getMovieId(), Movie.class);  //localhost:8082
	//		Movie movie=	restTemplate.getForObject("http://producerinfo/movies/"+r.getMovieId(), Movie.class);  //localhost:8082
		//	Movie movie=	webClientBuilder.build().get().uri("http://localhost:8082/movies/"+r.getMovieId()).retrieve().bodyToMono(Movie.class).block();
			catalogItems.add(new CatalogItem(movie.getName(), "desc:"+port, r.getRating()));
		}
		return catalogItems;
		
		
		
//		return ratings.stream().map( rating -> { 
//		Movie movie=	restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
//			new CatalogItem(movie.getName(), "desc", rating.getRating());
//			}).collect(Collectors.toList());
		// put them all together
		
//		return Collections.singletonList
//				(new CatalogItem("Transformer", "Test", 4));
		
	}
}
