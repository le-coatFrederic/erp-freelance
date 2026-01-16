import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { HistoryService } from '../../services/history-service';
import { HistoryChangeTypeMapper } from '../../models/values/history-change-type';

@Component({
  selector: 'app-history-list',
  imports: [RouterLink],
  templateUrl: './history-list.html',
  styleUrl: './history-list.scss',
})
export class HistoryList {
  private service = inject(HistoryService);
  history = toSignal(this.service.getAll(), { initialValue: [] });
  changeTypeMapper = HistoryChangeTypeMapper;
}
