import {User} from './user';
import {TransportEntity} from './transport';

export class RouteEntity {
  id: string;
  name: string;
  routeNumber: string;
  transportList: TransportEntity[];
}
