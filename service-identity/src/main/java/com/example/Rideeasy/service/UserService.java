@service
public  class UserService {
    @AutoWired

    private userRepository userRepositor;

    @CircuitBreaker(name ="dbService", fallbackMethod = "dbFallback" )
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    public User dbFallback(Long id, Throwable t) {
        return new User(id, "Guest User (System in Maintenance)");
    }

}