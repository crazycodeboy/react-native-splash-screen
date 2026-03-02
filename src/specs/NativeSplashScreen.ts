import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  show(): void;
  hide(): void;
}

export default TurboModuleRegistry.get<Spec>('SplashScreen');
