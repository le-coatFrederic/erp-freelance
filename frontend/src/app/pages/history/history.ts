import { Component } from '@angular/core';
import { HistoryList } from '../../components/history-list/history-list';

@Component({
  selector: 'app-history',
  imports: [HistoryList],
  templateUrl: './history.html',
  styleUrl: './history.scss',
})
export class History {}
