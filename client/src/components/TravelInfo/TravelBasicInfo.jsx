import React, { useEffect, useImperativeHandle, forwardRef, useContext } from "react";
import MaterialTable from "material-table";
import { DataContext } from "../../contexts/DataContext";
import DataContextProvider from "../../contexts/DataContext";
import { dataService } from "../../services/DataService";
import GetAppIcon from '@material-ui/icons/GetApp';
import BackupIcon from '@material-ui/icons/Backup';
import IconButton from "@material-ui/core/IconButton";
import { modalConstants } from "../../constants/ModalConstants";
import AddBox from '@material-ui/icons/AddBox';
import ArrowDownward from '@material-ui/icons/ArrowDownward';
import Check from '@material-ui/icons/Check';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Edit from '@material-ui/icons/Edit';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Remove from '@material-ui/icons/Remove';
import SaveAlt from '@material-ui/icons/SaveAlt';
import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';

const tableIcons = {
  Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
  Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
  Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
  DetailPanel: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
  Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
  Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
  Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
  FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
  LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
  NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
  PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
  ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
  SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
  ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
  ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />)
};
const TravelBasicInfo = forwardRef((props, ref) => {
  const { dataState, dispatch } = useContext(DataContext);
  useImperativeHandle(
    ref,
    () => ({
      getTravelInfoBetwenDates() {
        let datesRequest = {
          startDate: props.startDate,
          endDate: props.endDate,
        };
        someFetchActionCreator(datesRequest)
      }
    }),
  )
  const someFetchActionCreator = (datesRequest) => {

    const getTravelBasicInfoHandler = async () => {
      await dataService.findAllTravelRequests(datesRequest, dispatch);
    };
    getTravelBasicInfoHandler();
  }

  useEffect(() => {
    let datesRequest = {
      startDate: "",
      endDate: "",
    };
    someFetchActionCreator(datesRequest)
  }, [props.dispatch]);

  const handleDownloadDecisionAsExel = (event, travelReqID) => {
    event.stopPropagation();
    dataService.getDecisionAsExel(travelReqID, dispatch);
  }

  const handleOpenUploadFilesModal = (event, travelReqID) => {
    event.stopPropagation();
    dispatch({ type: modalConstants.SHOW_UPLOAD_FILES_MODAL, travelReqID });
  }

  const handleCalculateCostsForTravel = (event, travelReqID) => {
    event.stopPropagation();
    dataService.getCalculationAsExel(travelReqID, dispatch);
  }

  return (
    <div class="p-4">
      <DataContextProvider>
        <MaterialTable stickyHeader
          icons={tableIcons}
          columns={[
            { title: "Name", field: "employee.firstName" },
            { title: "Surname", field: "employee.lastName" },
            { title: "Travel request ID", field: "travelReqID" },
            { title: "Departing from", field: "departingCountry", type: 'string' },
            { title: "Date for departure", field: "departureDate", type: 'string' },
            { title: "Arriving to", field: "arrivalCountry", type: 'string' },
            { title: "Date for arrival", field: "arrivalDate", type: 'string' },
            { title: "Return trip", field: "returnTrip", type: 'string' },

          ]}
          actions={[
            {
              icon: () => <GetAppIcon />,
              title: 'Download decision',
              onClick: (event, rowData) => handleDownloadDecisionAsExel(event, rowData.travelReqID)
            },
            {
              icon: () => <BackupIcon />,
              title: 'Upload files',
              onClick: (event, rowData) => handleOpenUploadFilesModal(event, rowData.travelReqID)
            },
            {
              icon: () => <Check />,
              title: 'Upload files',
              onClick: (event, rowData) => handleCalculateCostsForTravel(event, rowData.travelReqID)
            },

          ]}
            options={{
              actionsColumnIndex: -1 ,
              headerStyle: { position: 'sticky', top: 0 },
              maxBodyHeight: 500,
          }}

          localization={{
            header: {
              actions: 'Decision - upload',
            },

          }}

          data={dataState.listTravelInfo.travelInfo}

          title="Travel information"
          onRowClick={(event, rowData) => { props.handleShowModal(rowData) }}

        />
      </DataContextProvider>
    </div>
  );
});

export default TravelBasicInfo;