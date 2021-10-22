package com.example.TravelApp.Unit;

import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.Position;
import com.example.TravelApp.Repository.PositionRepository;
import com.example.TravelApp.Service.Implementations.PositionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static com.example.TravelApp.Unit.Constants.PositionConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionTests {
    @Mock
    private PositionRepository positionRepositoryMock;

    @Mock
    private Position positionMock;

    @InjectMocks
    private PositionServiceImpl positionServiceMock;

    @Mock
    private PositionServiceImpl positionServiceMockNotInjected;

    @Test
    public void testShouldFindPositionByName() {
        when(positionRepositoryMock.findByName(POSITION_NAME)).thenReturn(positionMock);

        Position positionDb = positionServiceMock.findByName(POSITION_NAME);

        assertEquals(positionMock, positionDb);
        verify(positionRepositoryMock, times(1)).findByName(POSITION_NAME);
        verifyNoMoreInteractions(positionRepositoryMock);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotFindPositionByName() {
        Position positionForCreate = new Position(ID,POSITION_NAME, null);

        when(positionRepositoryMock.save(positionForCreate)).thenReturn(positionForCreate);
        when(positionRepositoryMock.findByName(POSITION_NAME)).thenThrow(new NullPointerException("Error"));

        positionRepositoryMock.save(positionForCreate);
        Position position = positionRepositoryMock.findByName(POSITION_NAME);
        assertNull(position);
    }

    @Test
    public void testShouldGetTranslatedPositionForEmployee() {
        Employee employee = new Employee(1,"123456","Jelena.Macet@synechron.com","Jelena", "Macet", "Associate - Technology",null,null,null,null);

        Position position = new Position(ID,POSITION_NAME, null);
        when(positionRepositoryMock.save(position)).thenReturn(position);
        when(positionServiceMock.getTranslatedPositionForEmployee(employee)).thenReturn(position);

        positionRepositoryMock.save(position);
        Position positionDb = positionServiceMock.getTranslatedPositionForEmployee(employee);

        assertEquals(positionDb.getName(),POSITION_NAME);
    }
    @Test(expected = NullPointerException.class)
    public void testShouldNotGetTranslatedPositionForEmployee() {
        Employee employee = new Employee(1,"123456","Jelena.Macet@synechron.com","Milana", "Macet", "Associate - Technology",null,null,null,null);

        Position position = new Position(ID,POSITION_NAME, null);
        when(positionRepositoryMock.save(position)).thenReturn(position);
        when(positionServiceMockNotInjected.getTranslatedPositionForEmployee(employee)).thenThrow(new NullPointerException("Error"));

        positionRepositoryMock.save(position);
        Position positionDb = positionServiceMockNotInjected.getTranslatedPositionForEmployee(employee);
        assertNull(positionDb);
    }

    @Test
    public void testShouldCreatePositionForEmployee() {
        List<Position> positionList = getPositionList();
        Position positionForCreate = new Position(ID,POSITION_NAME, null);

        when(positionRepositoryMock.findAll()).thenReturn(positionList);
        when(positionRepositoryMock.save(positionForCreate)).thenReturn(positionForCreate);

        int dbSizeBeforeCreate = positionRepositoryMock.findAll().size();
        Position positionCreated = positionRepositoryMock.save(positionForCreate);

        positionList.add(positionCreated);
        when(positionRepositoryMock.findAll()).thenReturn(positionList);

        assertEquals(positionRepositoryMock.findAll().size(),dbSizeBeforeCreate + 1);

        assertNotNull(positionCreated);
        verify(positionRepositoryMock, times(2)).findAll();
        verify(positionRepositoryMock, times(1)).save(positionForCreate);

    }
}
