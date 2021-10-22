import DatePicker from 'react-datepicker';
import moment from 'moment'

const DatesSelector = ({startDate, StartDateChange, endDate, EndDateChange,onChangeDates}) => {

	return (
        <div>
            <input
            type="date"
                onChange={e=>{StartDateChange(e)}}
                value={ moment(startDate).format("YYYY-MM-DD") } 
                max={moment(endDate).format("YYYY-MM-DD")}
                
            />
            <input
             type="date"
                onChange={e=>{EndDateChange(e)}}
                value={ moment(endDate).format("YYYY-MM-DD") } 
                min={moment(startDate).format("YYYY-MM-DD")}
            />
            <button onClick={onChangeDates}>
                OK
            </button>
        </div>  
	);
};

export default DatesSelector;
