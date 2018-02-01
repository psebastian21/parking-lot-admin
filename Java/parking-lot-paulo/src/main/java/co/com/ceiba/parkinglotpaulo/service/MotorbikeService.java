package co.com.ceiba.parkinglotpaulo.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.ceiba.parkinglotpaulo.domain.Motorbike;
import co.com.ceiba.parkinglotpaulo.repository.MotorbikeRepository;
import co.com.ceiba.parkinglotpaulo.utils.ITimeSource;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

@Service
public class MotorbikeService implements IMotorbikeService{
	//Constants
	@Value("${motorbikeService.maximumMotorbikeCountReached}")
	private String maximumMotorbikeCountReachedMessage;
	@Value("${motorbikeService.motorbikeCapacity}")
	private int motorbikeCapacity;
	@Value("${motorbikeService.hourlyRate}")
	private double hourlyRate;
	@Value("${motorbikeService.dailyRate}")
	private double dailyRate;
	@Value("${motorbikeService.motorbikeIsAlreadyParked}")
	private String motorbikeIsAlreadyParked;
	@Value("${motorbikeService.motorbikeCanParkOnlyInSundaysOrMondays}")
	private String motorbikeCanParkOnlyInSundaysOrMondays;
	@Value("${motorbikeService.motorbikeIsNotParked}")
	private String motorbikeIsNotParked;
	
	@Autowired
	private MotorbikeRepository motorbikeRepository;
	@Autowired
	private ITimeSource timeSource;
	

	@Override
	public Motorbike takeMotorbikeIn(Motorbike motorbike) throws ParkingException, ParseException {
		boolean plateCanParkToday = checkIfPlateCanParkToday(motorbike.getPlate());
		if(!plateCanParkToday) {
			throw new ParkingException(motorbikeCanParkOnlyInSundaysOrMondays);
		}
		Integer parkedMotorbikesCount = motorbikeRepository.countParkedMotorbikes();
		if (parkedMotorbikesCount >= motorbikeCapacity) {
			throw new ParkingException(maximumMotorbikeCountReachedMessage);
		}
		Motorbike motorbikeCheck = motorbikeRepository.checkIfMotorbikeIsAlreadyParked(motorbike.getPlate());
		if(motorbikeCheck != null) {
			throw new ParkingException(motorbikeIsAlreadyParked);
		}
		motorbike.setEntranceTime(new Date(timeSource.currentTimeMillis()));
		return motorbikeRepository.saveAndFlush(motorbike);
	}

	private boolean checkIfPlateCanParkToday(String plate) throws ParseException {
		if (plate.toUpperCase().charAt(0) != 'A') {
			return true;
		}
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timeSource.currentTimeMillis()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 || dayOfWeek == 2;
	}

	@Override
	public Motorbike getMotorbikeOut(String plate) throws ParkingException, ParseException {
		Motorbike m = motorbikeRepository.checkIfMotorbikeIsAlreadyParked(plate);
		if (m == null) {
			throw new ParkingException(motorbikeIsNotParked);
		}
		m.setExitTime(new Date(timeSource.currentTimeMillis()));
		m.calculateFee(dailyRate, hourlyRate);
		return motorbikeRepository.saveAndFlush(m);
	}

	public MotorbikeRepository getMotorbikeRepository() {
		return motorbikeRepository;
	}

	public void setMotorbikeRepository(MotorbikeRepository motorbikeRepository) {
		this.motorbikeRepository = motorbikeRepository;
	}
	@Override
	public ITimeSource getTimeSource() {
		return timeSource;
	}
	@Override
	public void setTimeSource(ITimeSource timeSource) {
		this.timeSource = timeSource;
	}
}
