import {User} from './user';
import {RouteEntity} from './route';

export class TransportEntity {
  id: string;
  name: string;
  transportType: string;
  numberPlate: string;
  currentRouteAssign: RouteEntity;
  currentAssignUser: User;
  creationTime: string;
  modificationTime: string;
  version: number;
}
