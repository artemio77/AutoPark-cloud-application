import {User} from './user';

export class TransportEntity {
  id: string;
  'name': string;
  'transportType': string;
  'numberPlate': string;
  'currentRouteAssign': any;
  'currentAssignUser': User;
  'creationTime': string;
  'modificationTime': string;
  'version';
}
